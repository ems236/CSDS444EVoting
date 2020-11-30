package com.therealergo.csds444frontend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.ProcessBuilder.Redirect;

import org.json.JSONObject;

import com.therealergo.main.Main;
import com.therealergo.main.MainException;

public class BackendInterface {
	private final Process backendProc;
	private final BufferedReader backendOutputReader;
	private final BufferedWriter backendOutputWriter;
	
	public final String voterId;
	
	public String ballotData                   ;
	public String committedBlindedVote         ;
	public String committedBlindedVoteSignature;
	public String adminBlindSignature          ;
	public String committedUnblindedVote       ;
	public String adminUnblindedSignature      ;
	public String lastVoteUnlocked             ;
	public String voteList                     ;
	public String committedUnblindedVote0      ;
	public String committedUnblindedVote1      ;
	public String committedUnblindedVote2      ;
	
	public JSONObject results;
	
	public BackendInterface() {
		
		// Start the backend Python process
		try {
			ProcessBuilder backendProcBuilder = new ProcessBuilder()
					
					// Enable this line to run the backend from an in-workspace build
					.directory(Main.resource.getResourceFolderLocal("").getParent().getParent().getParent().toFile())
					
					.command("python", "./main.py");
			backendProcBuilder.redirectError(Redirect.INHERIT);
			backendProcBuilder.redirectOutput(Redirect.PIPE);
			this.backendProc = backendProcBuilder.start();
			
			// Setup to read from / write to backend's output
			this.backendOutputReader = new BufferedReader(new InputStreamReader (backendProc.getInputStream ()));
			this.backendOutputWriter = new BufferedWriter(new OutputStreamWriter(backendProc.getOutputStream()));
			
			// Read the voter id that's immediately spit out by the backend
			backendOutputWriter.write("\n");
			backendOutputWriter.flush();
			this.voterId = backendOutputReader.readLine();
			
			// All of these values are null until the backend sends them over
			this.committedBlindedVote          = null;
			this.committedBlindedVoteSignature = null;
			
		} catch (IOException e) {
			throw new MainException(App.class, "Internal failure while starting backend process!", e);
		}
	}
	
	public String readVoterId() {
		return voterId;
	}
	
	public void writeBallotData(String ballotData) {
		this.ballotData = ballotData;
		try {
			
			// Write the ballot data
			backendOutputWriter.write(ballotData.replace("\n", "") + "\n");
			backendOutputWriter.flush();
			
			// Now that the backend has all of the ballot data, it will send back everything else
			JSONObject line0 = new JSONObject(backendOutputReader.readLine());
			String     line1 =                backendOutputReader.readLine() ;
			JSONObject line2 = new JSONObject(backendOutputReader.readLine());
			JSONObject line3 = new JSONObject(backendOutputReader.readLine());
			String     line4 =                backendOutputReader.readLine() ;
			JSONObject line5 = new JSONObject(backendOutputReader.readLine());
			
			committedBlindedVote          = (String) line0.get("committed_vote");
			committedBlindedVoteSignature = (String) line0.get("signature");
			adminBlindSignature           = line1.substring(1, line1.length() - 1);
			committedUnblindedVote        = (String) line2.get("committed_vote");
			adminUnblindedSignature       = (String) line2.get("admin_signature");
			voteList                      = line3.get("votes").toString();
			committedUnblindedVote0       = line3.getJSONArray("votes").getJSONArray(0).get(0).toString();
			committedUnblindedVote1       = line3.getJSONArray("votes").getJSONArray(1).get(0).toString();
			committedUnblindedVote2       = line3.getJSONArray("votes").getJSONArray(2).get(0).toString();
			lastVoteUnlocked              = line4;
			results                       = line5;
			
			Main.log.log(results.getJSONObject("prop2").getInt("No"));
			
		} catch (IOException e) {
//			throw new MainException(App.class, "Failed in backend ballot data I/O!", e);
		}
	}
	
	public void kill() {
		try {
			backendOutputReader.close();
		} catch (IOException e) {
		}
		backendProc.destroyForcibly();
	}
}
