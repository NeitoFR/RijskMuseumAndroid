package com.example.rijksmuseum.controllers;

public class MainViewController {
//    private App main;
//    private ArrayList<Oeuvre> listOeuvres;
//    private static ObservableList<String> selectableList = FXCollections.observableArrayList();
//    //private int imageCount;
//    private IntegerProperty cpt = new SimpleIntegerProperty();
//    @FXML
//    private ImageView artwork;
//    @FXML
//    private Button next, prev;
//    @FXML
//    private Label Lartiste, Ltitre, Ldate;
//    @FXML
//    private ListView<String> selectList;
//
//    public MainViewController() {
//
//    }
//
//    @FXML
//    private void initialize() {
//        cpt.set(0);
//        prev.setDisable(true);
//        cpt.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
//            System.out.println("Old value : "+oldValue+"new value : "+newValue+" Obs : "+ observable.toString());
//            if(newValue.intValue() == 0)
//            {
//                prev.setDisable(true);
//            }
//            else
//            {
//                prev.setDisable(false);
//            }
//            if(newValue.intValue() == listOeuvres.size() -1)
//            {
//                next.setDisable(true);
//            }
//            else
//            {
//                next.setDisable(false);
//            }
//        });
//        //System.out.println(imageCount.get());
//        listOeuvres = museumAPI.getListOeuvres();
//        buildSelectableList(listOeuvres);
//        Image i = new Image(listOeuvres.get(cpt.intValue()).getUrl());
//        setDetails(listOeuvres.get(cpt.intValue()));
//        artwork.setImage(i);
//    }
//    private void buildSelectableList(ArrayList<Oeuvre> listOeuvres) {
//        System.out.println("*****"+listOeuvres.size());
//        for (Oeuvre oeuvre : listOeuvres) {
//
//            String res = (oeuvre.getIndex()+1)+" "+oeuvre.getTitre();
//            selectableList.add(res);
//
//        }
//        selectList.setItems(selectableList);
//        selectList.setOnMouseClicked(new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent event) {
//                String[] s = selectList.getSelectionModel().getSelectedItem().toString().split(" ");
//                System.out.println(Integer.parseInt(s[0]));
//                setOeuvreFromList(listOeuvres.get(Integer.parseInt(s[0])-1));
//            }
//        });
//
//    }
//
//    @FXML
//    private void handleSuivant() {
//        cpt.set(cpt.get()+1);
//        System.out.println(cpt.get());
//        Image i = new Image(listOeuvres.get(cpt.intValue()).getUrl());
//        setDetails(listOeuvres.get(cpt.intValue()));
//        artwork.setImage(i);
//    }
//    @FXML
//    private void handlePrecedent() {
//        cpt.set(cpt.get()-1);
//        System.out.println(cpt.get());
//        Image i = new Image(listOeuvres.get(cpt.intValue()).getUrl());
//        setDetails(listOeuvres.get(cpt.intValue()));
//        artwork.setImage(i);
//    }
//    private void setOeuvreFromList(Oeuvre o)
//    {
//        cpt.set(o.getIndex());
//        Image i = new Image(o.getUrl());
//        setDetails(o);
//        artwork.setImage(i);
//    }
//    private void setDetails(Oeuvre o) {
//        Lartiste.setText(o.getArtiste());
//        Ltitre.setText(o.getTitre());
//        Ldate.setText(o.getDate());
//    }
//    public void setMain(App main)
//    {
//        this.main = main;
//    }
}