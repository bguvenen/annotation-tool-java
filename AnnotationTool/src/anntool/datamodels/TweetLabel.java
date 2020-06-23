package anntool.datamodels;

import java.util.List;

import anntool.enums.ContentLabelCategory;
import anntool.enums.HsTypeLabelCategory;
import anntool.enums.TargetLabelCategory;

public class TweetLabel {
	private int participantId;
	private int tweetId;
	private boolean isHateSpeech;
	private List<HsTypeLabelCategory> hsTypes;
	private List<TargetLabelCategory> targets;
	private List<ContentLabelCategory> contents;
	private String quote;

	private String otherHsType;
	private String otherTarget;
	private String otherContent;

	public TweetLabel(int participantId, int tweetId, boolean isHateSpeech, List<HsTypeLabelCategory> hsTypes,
			List<TargetLabelCategory> targets, List<ContentLabelCategory> contents, String quote) {
		this.participantId = participantId;
		this.tweetId = tweetId;
		this.isHateSpeech = isHateSpeech;
		this.hsTypes = hsTypes;
		this.targets = targets;
		this.contents = contents;
		this.quote = quote;
	}

	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}

	public int getParticipantId() {
		return participantId;
	}

	public int getTweetId() {
		return tweetId;
	}

	public boolean isHateSpeech() {
		return isHateSpeech;
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

	public String getQuote() {
		return quote;
	}

	public String getOtherHsType() {
		return otherHsType;
	}

	public String getOtherTarget() {
		return otherTarget;
	}

	public String getOtherContent() {
		return otherContent;
	}

	public void setOtherHsType(String otherHsType) {
		this.otherHsType = otherHsType;
	}

	public void setOtherTarget(String otherTarget) {
		this.otherTarget = otherTarget;
	}

	public void setOtherContent(String otherContent) {
		this.otherContent = otherContent;
	}

	public String getHsTypesString() {
		if (hsTypes == null)
			return "";

		String str = "";

		for (HsTypeLabelCategory label : hsTypes) {
			if (label == HsTypeLabelCategory.OTHER) {
				str = str + label.toString() + " (" + otherHsType + "), ";
			} else {
				str = str + label.toString() + ", ";
			}
		}

		if (str.length() > 0)
			str = str.substring(0, str.length() - 2);
		return str;
	}

	public String getTargetsString() {
		if (targets == null)
			return "";

		String str = "";

		for (TargetLabelCategory label : targets) {
			if (label == TargetLabelCategory.OTHER) {
				str = str + label.toString() + " (" + otherTarget + "), ";
			} else {
				str = str + label.toString() + ", ";
			}
		}

		if (str.length() > 0)
			str = str.substring(0, str.length() - 2);
		return str;
	}

	public String getContentsString() {
		if (contents == null)
			return "";

		String str = "";

		for (ContentLabelCategory label : contents) {
			if (label == ContentLabelCategory.OTHER) {
				str = str + label.toString() + " (" + otherContent + "), ";
			} else {
				str = str + label.toString() + ", ";
			}
		}

		if (str.length() > 0)
			str = str.substring(0, str.length() - 2);
		return str;
	}

}
