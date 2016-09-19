package nju.financecity_android.controller.activity;

/**
 * Created by coral on 16-9-12.
 */
public interface IQuestionObserver {
    void putAnswer(String key, Object value);
    Object getAnswer(String key);
    void showNextPage();
    void showNextPage(int skip);
    void showFormerPage(int skip);
    void showFormerPage();
    void postAnswer();
}
