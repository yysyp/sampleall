set "pipeline-shared-lib2=D:\patrick\github-com\sampleall\jenkins\pipeline-shared-lib2"
cd ..
rd /S /Q %pipeline-shared-lib2%\
robocopy pipeline-shared-lib2 %pipeline-shared-lib2%\ /E /MIR /Z /XD "not-copy" "log" ".git" ".gitattributes" ".gitignore"
echo "Copy current pipeline-shared-lib2 to %pipeline-shared-lib2%\... done!"
