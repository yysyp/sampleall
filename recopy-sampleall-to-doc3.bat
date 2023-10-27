set "doc3-sampleall=..\doc3\3-learn\33-coding\sampleall"
cd ..
rd /S /Q %doc3-sampleall%\
robocopy sampleall %doc3-sampleall%\ /E /MIR /Z /XD "not-copy" "log" ".git" ".gitattributes"
echo 'Copy current sampleall to doc3\... done!'
