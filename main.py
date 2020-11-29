import os

dir0 = os.path.abspath('./backend')
dir1 = os.path.abspath('./utils')
if "PYTHONPATH" in os.environ:
    os.environ['PYTHONPATH'] = dir0 + os.pathsep + dir1 + os.pathsep + os.environ['PYTHONPATH']
else:
    os.environ['PYTHONPATH'] = dir0 + os.pathsep + dir1

from backend.main import main
main()