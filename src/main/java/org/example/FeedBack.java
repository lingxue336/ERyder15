package org.example;

public class FeedBack {



    private String firstName;
    private String email;
    private String reviewID ;
    private String lastName;
    private String completeFeedback;
    private boolean longFeedback;

    public FeedBack(String firstName,String email,String lastName){
        this.firstName=firstName;
        this.email=email;
        this.lastName=lastName;

    }
    public String getFirstName() {
        return firstName;
    }



    public String getEmail() {
        return email;
    }



    public String getLastName() {
        return lastName;
    }
    public void analyseFeedback(boolean isConcatenation,String sent1,String sent2,String sent3,String sent4,String sent5) {
        if (isConcatenation) {
            completeFeedback = feedbackUsingConcatenation(sent1, sent2, sent3, sent4, sent5);
            checkFeedbackLength(completeFeedback);
            createReviewID(firstName, lastName, completeFeedback);
        } else {
            completeFeedback = feedbackUsingStringBuilder(sent1, sent2, sent3, sent4, sent5).toString();
            checkFeedbackLength(completeFeedback);
            createReviewID(firstName, lastName, completeFeedback);
        }
    }
    private String feedbackUsingConcatenation(String sent1,String sent2,String sent3,String sent4,String sent5){
        String concatenatedFeedback=sent1+sent2+sent3+sent4+sent5;
        return concatenatedFeedback;

    }
    private String feedbackUsingStringBuilder(String sent1,String sent2,String sent3,String sent4,String sent5) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(sent1);
        stringBuilder.append(sent2);
        stringBuilder.append(sent3);
        stringBuilder.append(sent4);
        stringBuilder.append(sent5);
        return stringBuilder.toString();
    }
    public String toString(){
        return "Feedback Information:\n" +
                "First Name: " + firstName + "\n" +
                "Last Name: " + lastName + "\n" +
                "Email: " + email + "\n" +
                "Complete Feedback: " + completeFeedback + "\n" +
                "Is Long Feedback (＞500 chars): " + longFeedback + "\n" +
                "Unique Review ID: " + reviewID;

    }


    private boolean checkFeedbackLength(String feedback) {
        if (feedback.length() > 500) {
            longFeedback = true;
            return longFeedback;

        } else {
            longFeedback = false;
            return longFeedback;

        }


    }
    private String createReviewID(String firstName,String lastName,String feedback){

        reviewID = ((firstName+lastName).toUpperCase().substring(2,6)+completeFeedback.substring(10,15).toLowerCase()+completeFeedback.length()+"_"+System.currentTimeMillis()).replace("","");

        return reviewID;

    }
}
