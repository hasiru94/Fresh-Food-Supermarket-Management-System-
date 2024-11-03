package cwead1.controller;

import cwead1.Model.LoadingController;
import cwead1.Model.LoadingModel;
import cwead1.Model.LoadingView;

public class Main {
    public static void main(String[] args) {
        LoadingModel model = new LoadingModel();
        LoadingView view = new LoadingView();
        LoadingController controller = new LoadingController(model, view);

        controller.startLoading();
    }
}

