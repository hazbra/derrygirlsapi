package com.derrygirls.view;

public class View {

    //Enclosing type to define User views
    public static interface CharacterView {

        public static interface PartialView {
        }

        public static interface FullView extends PartialView {
        }
    }
}
