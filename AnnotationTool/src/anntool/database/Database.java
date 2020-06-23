package anntool.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import anntool.datamodels.Participant;
import anntool.datamodels.Record;
import anntool.datamodels.Tweet;
import anntool.datamodels.TweetLabel;
import anntool.enums.ContentLabelCategory;
import anntool.enums.HsTypeLabelCategory;
import anntool.enums.TargetLabelCategory;

public class Database {
	private static Gson gson;

	public static void wakeUp() {

		String appPath = Database.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		int startingIndex = appPath.indexOf("C:");
		
		int dividerIndex = appPath.lastIndexOf("/");
		if(dividerIndex > 0)
		{
			DataConstants.setSeparator("/");
		}
		else
		{
			dividerIndex = appPath.lastIndexOf("\\");
			DataConstants.setSeparator("\\");
		}
		
		String mainDirParent = appPath.substring(startingIndex, dividerIndex);
		DataConstants.setMainDirectoryParent(mainDirParent);
		gson = new GsonBuilder().create();

		if (!databaseExist()) {
			setUpDirectories();
		}
	}

	// tweets

	public static boolean addNewTweet(Tweet tweet) {
		int tweetId = tweet.getTweetId();

		if (tweetExists(tweetId))
			return false;

		String tweetDir = DataConstants.getTweetDir(tweetId);
		String tweetLabelDir = DataConstants.getTweetLabelsDir(tweetId);
		DatabaseIO.createDirectory(tweetDir);
		DatabaseIO.createDirectory(tweetLabelDir);

		String tweetJsonText = gson.toJson(tweet);
		String tweetFilePath = DataConstants.getTweetFilePath(tweetId);

		return DatabaseIO.writeToFile(tweetFilePath, tweetJsonText);
	}

	public static boolean saveTweet(Tweet tweet) {
		int tweetId = tweet.getTweetId();

		if (!tweetExists(tweetId))
			return false;

		String tweetJsonText = gson.toJson(tweet);
		String tweetFilePath = DataConstants.getTweetFilePath(tweetId);

		return DatabaseIO.writeToFile(tweetFilePath, tweetJsonText);
	}

	public static boolean tweetExists(int tweetId) {
		String tweetFilePath = DataConstants.getTweetFilePath(tweetId);
		return DatabaseIO.fileExists(tweetFilePath);
	}

	public static Tweet getTweet(int tweetId) {
		String tweetFilePath = DataConstants.getTweetFilePath(tweetId);
		String tweetJsonText = DatabaseIO.readFromFile(tweetFilePath);
		Tweet tweet = gson.fromJson(tweetJsonText, Tweet.class);
		return tweet;
	}

	public static int getTweetCount() {
		return DatabaseIO.getSubDirectoryCount(DataConstants.getTweetsMainDir());
	}

	public static List<Tweet> getTweetList() {
		List<Tweet> tweetList = new ArrayList<Tweet>();
		int count = getTweetCount();

		for (int i = 1; i <= count; i++) {
			tweetList.add(getTweet(i));
		}

		return tweetList;
	}

	public static boolean addLabel(TweetLabel label) {
		int tweetId = label.getTweetId();
		int participantId = label.getParticipantId();
		String labelJsonText = gson.toJson(label);
		String labelFilePath = DataConstants.getTweetLabelFilePath(tweetId, participantId);

		return DatabaseIO.writeToFile(labelFilePath, labelJsonText);
	}

	public static TweetLabel getLabel(int tweetId, int participantId) {
		String labelFilePath = DataConstants.getTweetLabelFilePath(tweetId, participantId);
		String labelJsonText = DatabaseIO.readFromFile(labelFilePath);
		TweetLabel label = gson.fromJson(labelJsonText, TweetLabel.class);
		return label;
	}

	public static List<TweetLabel> getTweetLabelList(int tweetId) {
		String labelsDir = DataConstants.getTweetLabelsDir(tweetId);
		String[] labelFileNames = DatabaseIO.getFileNames(labelsDir);

		List<TweetLabel> labelList = new ArrayList<TweetLabel>();

		for (String str : labelFileNames) {
			int participantId = Integer.parseInt(str.replace(".txt", ""));
			TweetLabel label = getLabel(tweetId, participantId);

			labelList.add(label);
		}

		return labelList;
	}

	public static int getTweetLabelCount(int tweetId) {
		String labelsDir = DataConstants.getTweetLabelsDir(tweetId);
		return DatabaseIO.getSubDirectoryCount(labelsDir);
	}

	public static boolean addParticipant(Participant participant) {
		int participantId = participant.getParticipantId();

		String participantJsonText = gson.toJson(participant);
		String participantFilePath = DataConstants.getParticipantFilePath(participantId);

		return DatabaseIO.writeToFile(participantFilePath, participantJsonText);
	}

	public static Participant getParticipant(int participantId) {
		String participantFilePath = DataConstants.getParticipantFilePath(participantId);
		String participantJsonText = DatabaseIO.readFromFile(participantFilePath);
		Participant participant = gson.fromJson(participantJsonText, Participant.class);
		return participant;
	}

	public static boolean participantExists(int participantId) {
		String participantFilePath = DataConstants.getParticipantFilePath(participantId);
		return DatabaseIO.fileExists(participantFilePath);
	}

	public static List<Participant> getParticipantList() {
		String[] participantFileNames = DatabaseIO.getFileNames(DataConstants.getParticipantsMainDir());

		List<Participant> participantList = new ArrayList<Participant>();

		for (String str : participantFileNames) {
			int participantId = Integer.parseInt(str.replace(".txt", ""));

			Participant participant = getParticipant(participantId);
			participantList.add(participant);
		}

		return participantList;
	}

	public static int getParticipantCount() {
		String participantsDir = DataConstants.getParticipantsMainDir();
		return DatabaseIO.getSubFileCount(participantsDir);
	}

	public static void setUpForRecords() {
		DatabaseIO.createDirectory(DataConstants.getRecordDir());
		DatabaseIO.createDirectory(DataConstants.getLabelRecordDir());
	}

	public static boolean recordDirectoriesExist() {
		return DatabaseIO.dirExists(DataConstants.getRecordDir());
	}

	public static boolean addParticipantRecord(Participant participant) {
		String participantJsonText = gson.toJson(participant);
		String participantRecordFilePath = DataConstants.getParticipantRecordFilePath();

		return DatabaseIO.writeToFile(participantRecordFilePath, participantJsonText);
	}

	public static boolean participantRecordExists() {
		String participantRecordFilePath = DataConstants.getParticipantRecordFilePath();
		return DatabaseIO.fileExists(participantRecordFilePath);
	}

	public static Participant getParticipantRecord() {
		String participantRecordFilePath = DataConstants.getParticipantRecordFilePath();
		String participantRecordJsonText = DatabaseIO.readFromFile(participantRecordFilePath);
		Participant participant = gson.fromJson(participantRecordJsonText, Participant.class);
		return participant;
	}

	public static boolean addLabelRecord(TweetLabel label) {
		String labelJsonText = gson.toJson(label);
		String labelRecordFilePath = DataConstants.getLabelRecordFilePath(label.getTweetId());

		return DatabaseIO.writeToFile(labelRecordFilePath, labelJsonText);
	}

	public static boolean labelRecordExists(int tweetId) {
		String labelRecordFilePath = DataConstants.getLabelRecordFilePath(tweetId);
		return DatabaseIO.fileExists(labelRecordFilePath);
	}

	public static TweetLabel getLabelRecord(int tweetId) {
		String labelRecordFilePath = DataConstants.getLabelRecordFilePath(tweetId);
		String labelRecordJsonText = DatabaseIO.readFromFile(labelRecordFilePath);
		TweetLabel labelRecord = gson.fromJson(labelRecordJsonText, TweetLabel.class);
		return labelRecord;
	}

	public static List<TweetLabel> getLabelRecordList() {
		String[] labelRecordFileNames = DatabaseIO.getFileNames(DataConstants.getLabelRecordDir());

		List<TweetLabel> labelRecordList = new ArrayList<TweetLabel>();

		for (String str : labelRecordFileNames) {
			int tweetId = Integer.parseInt(str.replace(".txt", ""));
			TweetLabel labelRecord = getLabelRecord(tweetId);

			labelRecordList.add(labelRecord);
		}
		return labelRecordList;
	}

	public static void exportRecord(String path) {
		Participant participant = getParticipantRecord();
		List<TweetLabel> labelRecords = getLabelRecordList();
		Record record = new Record(participant, labelRecords);

		String recordJsonText = gson.toJson(record);

		DatabaseIO.writeToFile(path, recordJsonText);
	}

	public static void unpackRecordFile(String path) {
		int participantId = Database.getParticipantCount() + 1001;
		String recordJsonText = DatabaseIO.readFromFile(path);
		Record record = gson.fromJson(recordJsonText, Record.class);

		Participant participant = record.getParticipant();
		participant.setParticipantId(participantId);
		Database.addParticipant(participant);

		List<TweetLabel> labelList = record.getLabelList();

		for (TweetLabel tl : labelList) {
			tl.setParticipantId(participantId);

			Tweet tweet = Database.getTweet(tl.getTweetId());
			tweet.increaseAnsCount();

			if (tl.isHateSpeech()) {
				for (HsTypeLabelCategory hsType : tl.getHsTypes()) {
					if (hsType == HsTypeLabelCategory.OTHER)
						tweet.addToOtherHsTypes(tl.getOtherHsType());
					else
						tweet.addToHsTypes(hsType);
				}

				for (TargetLabelCategory target : tl.getTargets()) {
					if (target == TargetLabelCategory.OTHER)
						tweet.addToOtherTargets(tl.getOtherTarget());
					else
						tweet.addToTargets(target);
				}

				for (ContentLabelCategory content : tl.getContents()) {
					if (content == ContentLabelCategory.OTHER)
						tweet.addToOtherContents(tl.getOtherContent());
					else
						tweet.addToContents(content);
				}
			}

			Database.saveTweet(tweet);
			Database.addLabel(tl);
		}
	}

	public static List<Tweet> readRawTweetListFromFile(String inputFilePath) {
		List<Tweet> tweetList = new ArrayList<Tweet>();
		int id = Database.getTweetCount();

		File fileDir = new File(inputFilePath);
		BufferedReader in;
		try {

			in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));

			String str;
			while ((str = in.readLine()) != null) {
				id++;
				Tweet tweet = new Tweet(id, str);
				tweetList.add(tweet);
			}

			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tweetList;
	}

	public static void writeTweetListToFile(String outputFilePath, List<Tweet> tweetList) {
		Collections.sort(tweetList, new Comparator<Tweet>() {
			@Override
			public int compare(Tweet t1, Tweet t2) {
				int comparison = t1.getTweetId() - t2.getTweetId();
				return comparison;
			}
		});

		List<String> tweetStrList = new ArrayList<String>();

		for (Tweet tweet : tweetList) {
			tweetStrList.add(tweet.getTweetText());
		}

		try {
			Path textFile = Paths.get(outputFilePath);
			Files.write(textFile, tweetStrList, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void setUpDirectories() {
		DatabaseIO.createDirectory(DataConstants.getMainDir());
		DatabaseIO.createDirectory(DataConstants.getTweetsMainDir());
		DatabaseIO.createDirectory(DataConstants.getParticipantsMainDir());
	}

	private static boolean databaseExist() {
		return DatabaseIO.fileExists(DataConstants.getMainDir());
	}
}
