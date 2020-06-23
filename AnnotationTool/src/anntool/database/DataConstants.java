package anntool.database;

public class DataConstants {

	private static String mainDir = "";
	private static String seperator = "";	
	
	protected static void setMainDirectoryParent(String mainDirParent)
	{
		mainDir = mainDirParent + seperator + "AnnToolDB";
	}
	
	protected static void setSeparator(String sep)
	{
		seperator = sep;
	}
	
	protected static String getMainDir()
	{
		return mainDir;
	}
	
	protected static String getTweetsMainDir()
	{
		return mainDir + seperator + "Tweets";
	}
	
	protected static String getParticipantsMainDir()
	{
		return mainDir + seperator + "Participants";
	}
	
	protected static String getRecordDir()
	{
		return mainDir + seperator + "Record";
	}
	
	protected static String getLabelRecordDir()
	{
		return getRecordDir() + seperator + "Answers";
	}
	
	protected static String getTweetDir(int tweetId) {
		return getTweetsMainDir() + seperator + tweetId;
	}

	protected static String getTweetFilePath(int tweetId) {
		return getTweetsMainDir() + seperator + tweetId + seperator + tweetId + ".txt";
	}

	protected static String getTweetLabelsDir(int tweetId) {
		return getTweetsMainDir() + seperator + tweetId + seperator + "Labels";
	}

	protected static String getTweetLabelFilePath(int tweetId, int participantId) {
		return getTweetsMainDir() + seperator + tweetId + seperator + "Labels" + seperator + participantId + ".txt";
	}

	protected static String getParticipantFilePath(int participantId) {
		return getParticipantsMainDir() + seperator + participantId + ".txt";
	}

	protected static String getParticipantRecordFilePath() {
		return getRecordDir() + seperator + "participant.txt";
	}

	protected static String getLabelRecordFilePath(int tweetId) {
		return getLabelRecordDir() + seperator + tweetId + ".txt";
	}
}
