package anntool.datamodels;

import java.util.ArrayList;
import java.util.List;

import anntool.enums.ContentLabelCategory;
import anntool.enums.HsTypeLabelCategory;
import anntool.enums.TargetLabelCategory;

public class Tweet {
	private int tweetId;
	private String tweetText;
	private int ansCount;
	private List<HsTypeLabelCategory> hsTypes;
	private List<TargetLabelCategory> targets;
	private List<ContentLabelCategory> contents;

	private List<String> otherHsTypes;
	private List<String> otherTargets;
	private List<String> otherContents;

	public Tweet(int tweetId, String tweetText) {
		this.tweetId = tweetId;
		this.tweetText = tweetText;
		this.ansCount = 0;
		this.hsTypes = new ArrayList<HsTypeLabelCategory>();
		this.targets = new ArrayList<TargetLabelCategory>();
		this.contents = new ArrayList<ContentLabelCategory>();
		this.otherHsTypes = new ArrayList<String>();
		this.otherTargets = new ArrayList<String>();
		this.otherContents = new ArrayList<String>();
	}

	public int getTweetId() {
		return tweetId;
	}

	public String getTweetText() {
		return tweetText;
	}

	public int getAnsCount() {
		return ansCount;
	}

	public void increaseAnsCount() {
		ansCount++;
	}

	public List<HsTypeLabelCategory> getHsTypes() {
		return hsTypes;
	}

	public List<TargetLabelCategory> getTargets() {
		return targets;
	}

	public List<ContentLabelCategory> getContents() {
		return contents;
	}

	public void addToHsTypes(HsTypeLabelCategory hsTypeLabel) {
		if (!hsTypes.contains(hsTypeLabel))
			hsTypes.add(hsTypeLabel);
	}

	public void addToTargets(TargetLabelCategory targetLabel) {
		if (!targets.contains(targetLabel))
			targets.add(targetLabel);
	}

	public void addToContents(ContentLabelCategory contentLabel) {
		if (!contents.contains(contentLabel))
			contents.add(contentLabel);
	}

	public void addToOtherHsTypes(String otherHsType) {
		if (!otherHsTypes.contains(otherHsType))
			otherHsTypes.add(otherHsType);
	}

	public void addToOtherTargets(String otherTarget) {
		if (!otherTargets.contains(otherTarget))
			otherTargets.add(otherTarget);
	}

	public void addToOtherContents(String otherContent) {
		if (!otherContents.contains(otherContent))
			otherContents.add(otherContent);
	}

	public String getHsTypesString() {
		String str = "";

		for (HsTypeLabelCategory label : hsTypes) {
			str = str + label.toString() + ", ";
		}

		for (String otherLabel : otherHsTypes) {
			str = str + "Diğer (" + otherLabel + "), ";
		}

		if (str.length() > 0)
			str = str.substring(0, str.length() - 2);
		return str;
	}

	public String getTargetsString() {
		String str = "";

		for (TargetLabelCategory label : targets) {
			str = str + label.toString() + ", ";
		}

		for (String otherLabel : otherTargets) {
			str = str + "Diğer (" + otherLabel + "), ";
		}

		if (str.length() > 0)
			str = str.substring(0, str.length() - 2);
		return str;
	}

	public String getContentsString() {
		String str = "";

		for (ContentLabelCategory label : contents) {
			str = str + label.toString() + ", ";
		}

		for (String otherLabel : otherContents) {
			str = str + "Diğer (" + otherLabel + "), ";
		}

		if (str.length() > 0)
			str = str.substring(0, str.length() - 2);
		return str;
	}

	public boolean areLabelListsEmpty() {
		return hsTypes.isEmpty() && targets.isEmpty() && contents.isEmpty() && otherHsTypes.isEmpty()
				&& otherTargets.isEmpty() && otherContents.isEmpty();
	}
}
