package cwead1.Model;

import cwead1.view.LoginPage;

public class LoadingController {

    private LoadingModel model;
    private LoadingView view;

    public LoadingController(LoadingModel model, LoadingView view) {
        this.model = model;
        this.view = view;
    }

    public void startLoading() {
        view.setVisible(true);

        // Simulate the loading process
        new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(40); // Delay for demonstration
                    model.setProgress(i);
                    view.setProgress(model.getProgress());
                }
                // Show the login page once loading is complete
                view.dispose();
                new LoginPage().setVisible(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
