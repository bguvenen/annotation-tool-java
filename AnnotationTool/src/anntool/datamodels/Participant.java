package anntool.datamodels;

public class Participant
{
    private int participantId;
    private String gender;
    private int age;
    private String nationality;
    private String educationalStatus;
    private String politicalViews;

    public Participant(int participantId, String gender, int age, String nationality, String educationalStatus,
            String politicalViews)
    {
        this.participantId = participantId;
        this.gender = gender;
        this.age = age;
        this.nationality = nationality;
        this.educationalStatus = educationalStatus;
        this.politicalViews = politicalViews;
    }

    public void setParticipantId(int participantId)
    {
        this.participantId = participantId;
    }

    public int getParticipantId()
    {
        return participantId;
    }

    public String getGender()
    {
        return gender;
    }

    public int getAge()
    {
        return age;
    }

    public String getNationality()
    {
        return nationality;
    }

    public String getEducationalStatus()
    {
        return educationalStatus;
    }

    public String getPoliticalViews()
    {
        return politicalViews;
    }
}
