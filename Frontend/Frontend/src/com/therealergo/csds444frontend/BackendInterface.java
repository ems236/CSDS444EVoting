package com.therealergo.csds444frontend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.ProcessBuilder.Redirect;

import com.therealergo.main.MainException;

public class BackendInterface {
	private final Process backendProc;
	private final BufferedReader backendOutputReader;
	private final BufferedWriter backendOutputWriter;
	
	public final String voterId;
	
	public String blindedCommittedVote         ;
	public String blindedCommittedVoteSignature;
	
	public BackendInterface() throws IOException {
		
		// Start the backend Python process
		ProcessBuilder backendProcBuilder = new ProcessBuilder().command("python", "./main.py");
		backendProcBuilder.redirectError(Redirect.INHERIT);
		backendProcBuilder.redirectOutput(Redirect.PIPE);
		this.backendProc = backendProcBuilder.start();
		
		// Setup to read from / write to backend's output
		this.backendOutputReader = new BufferedReader(new InputStreamReader (backendProc.getInputStream ()));
		this.backendOutputWriter = new BufferedWriter(new OutputStreamWriter(backendProc.getOutputStream()));
		
		// Read the voter id that's immediately spit out by the backend
		this.voterId = backendOutputReader.readLine();
		
		// All of these values are null until the backend sends them over
		this.blindedCommittedVote          = null;
		this.blindedCommittedVoteSignature = null;
	}
	
	private final String textEscape(String text) {
		return text.replace("\\", "\\\\").replace("\n", "\\n");
	}
	
	private final String textUnEscape(String text) {
		return text.replace("\\n", "\n").replace("\\\\", "\\");
	}
	
	public String readVoterId() {
		return voterId;
	}
	
	public void writeBallotData(String ballotData) {
		try {
			
			// Write the ballot data
			backendOutputWriter.write(textEscape(ballotData) + "\n");
			backendOutputWriter.flush();
			
			// Now that the backend has all of the ballot data, it will send back everything else
			blindedCommittedVote          = textUnEscape(backendOutputReader.readLine());
			blindedCommittedVoteSignature = textUnEscape(backendOutputReader.readLine());
			
		} catch (IOException e) {
			throw new MainException(App.class, "Failed in backend ballot data I/O!", e);
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
