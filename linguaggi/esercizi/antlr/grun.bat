@ECHO OFF
SET CLASSPATH=.;C:\AltriProgrammi\antlr\antlr-4.9.2-complete.jar;%CLASSPATH%
SET TEST_CURRENT_DIR=%CLASSPATH:.;=%
if "%TEST_CURRENT_DIR%" == "%CLASSPATH%" ( SET CLASSPATH=.;%CLASSPATH% )
@ECHO ON
java org.antlr.v4.gui.TestRig %*