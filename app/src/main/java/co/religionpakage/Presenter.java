package co.religionpakage;

public class  Presenter implements Contract.Presenter{
    private Contract.View view;
    Contract.Model model = new Model();
    @Override
    public void attachView(Contract.View view) {

        this.view = view;
        model.attachPresenter(this);
    }

    @Override
    public void getLogin(String query) {

    }

    @Override
    public void onReceived(String model) {

    }

    @Override
    public void receivedFailed(String msg) {

    }
}
