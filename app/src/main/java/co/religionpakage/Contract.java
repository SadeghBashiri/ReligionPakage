package co.religionpakage;

public interface Contract {

    interface View {
        void onReceived(String model);
        void receivedFailed(String msg);
    }

    interface Presenter {
        void attachView(View view);
        void getLogin(String query);
        void onReceived(String model);
        void receivedFailed(String msg);
    }

    interface Model {
        void attachPresenter(Presenter presenter);
        void getLogin(String query);
    }
}
