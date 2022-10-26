package com.uet.int2204.group2.Menu;

import com.uet.int2204.group2.Bomberman;
import com.uet.int2204.group2.Sound.Sound;
import com.uet.int2204.group2.component.GameState;
import com.uet.int2204.group2.utils.ResourceManager;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


public class GameMenu extends Parent {
    GameState game = new GameState();
    public static Sound sound = new Sound();
    public static Sound effec = new Sound();
    public static final int count = 0;

    public GameMenu() {
        VBox menuStart = new VBox(30);
        VBox instruction = new VBox(30);
        VBox pause = new VBox(30);
        HBox icon = new HBox();
        HBox icon2 = new HBox();
        HBox icon3 = new HBox();
        HBox icon4 = new HBox();
        menuStart.setTranslateX(170);
        menuStart.setTranslateY(220);
        instruction.setTranslateX(0);
        instruction.setTranslateY(200);
        pause.setTranslateX(170);
        pause.setTranslateY(220);
        icon.setTranslateX(150);
        icon.setTranslateY(650);
        icon2.setTranslateX(150);
        icon2.setTranslateY(650);
        icon3.setTranslateX(400);
        icon3.setTranslateY(650);
        icon4.setTranslateX(400);
        icon4.setTranslateY(650);
        final int offset = 400;
        /**
         * Menu start.
         */
        MenuButton btnStart = new MenuButton("START");
        MenuButton btnOptions = new MenuButton("INSTRUCTION");
        MenuButton btnExit = new MenuButton("EXIT");
        MenuButton btnBack = new MenuButton("BACK");
        btnBack.setTranslateX(-100);
        /**
         * Menu Pause.
         */
        MenuButton btnResume = new MenuButton("RESUME");
        MenuButton btnNewGame = new MenuButton("NEWGAME");
        MenuButton btnQuit = new MenuButton("Quit");

        /**
         * Menu option.
         */
        Instruction btnInstruction = new Instruction(" Use the direction of key" + "\n"
                + " to move the kernel, the" + "\n"
                + " key space to set boom");
        /**
         * Sound
         */
        IconSound iconSound = new IconSound("");
        IconSoundMute iconSoundMute = new IconSoundMute("");
        IconSound iconSound1 = new IconSound("");
        IconSoundMute iconSoundMute1 = new IconSoundMute("");

        iconSound.setOnMouseClicked(mouseEvent -> {
            getChildren().add(icon2);
            getChildren().remove(icon);
            Bomberman.start.stopMusic();
        });
        iconSoundMute.setOnMouseClicked(mouseEvent -> {
            getChildren().add(icon);
            getChildren().remove(icon2);
            Bomberman.start.playMusic(ResourceManager.sound[0], true);
        });
        iconSound1.setOnMouseClicked(mouseEvent -> {
            getChildren().add(icon4);
            getChildren().remove(icon3);
            effec.stopMusic();
        });
        iconSoundMute1.setOnMouseClicked(mouseEvent -> {
            getChildren().add(icon3);
            getChildren().remove(icon4);
            effec.playMusic(ResourceManager.sound[1],true);
        });

        btnStart.setOnMouseClicked(event -> {
            Bomberman.start.stopMusic();
            effec.playMusic(ResourceManager.sound[1], true);
            effec.changeVolume(0.5);
            effec.loopMusic();
            switchToGame();
        });

        btnNewGame.setOnMouseClicked(event -> {
            effec.stopMusic();
            effec.playMusic(ResourceManager.sound[1], true);
            effec.changeVolume(0.5);
            effec.loopMusic();
            getChildren().remove(Bomberman.root);
            getChildren().remove(pause);
            getChildren().remove(icon3);
            getChildren().remove(icon4);
            game.reload();
            switchToGame();
        });

        btnOptions.setOnMouseClicked(event -> {
            getChildren().add(instruction);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), menuStart);
            tt.setToX(menuStart.getTranslateX() - 400);
            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), instruction);
            tt1.setToX(menuStart.getTranslateX());
            tt.play();
            tt1.play();
            tt.setOnFinished(evt -> {
                getChildren().remove(menuStart);
            });
        });

        btnBack.setOnMouseClicked(event -> {
            getChildren().add(menuStart);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), menuStart);
            tt.setToX(menuStart.getTranslateX() + offset);
            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), instruction);
            tt1.setToX(instruction.getTranslateX());
            tt.play();
            tt1.play();
            tt.setOnFinished(evt -> {
                getChildren().remove(instruction);
            });
        });

        Bomberman.scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                game.stop();
                getChildren().remove(menuStart);
                getChildren().add(pause);
                getChildren().add(icon3);
                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), instruction);
                tt1.setToX(menuStart.getTranslateX());
                Bomberman.setRoot(Bomberman.root);
            }
        });

        btnExit.setOnMouseClicked(event -> {
            Bomberman.closeApp();
        });
        btnQuit.setOnMouseClicked(event -> {
            Bomberman.closeApp();
        });
        btnResume.setOnMouseClicked(mouseEvent -> {
            Bomberman.start.stopMusic();
            getChildren().remove(pause);
            getChildren().remove(icon3);
            getChildren().remove(icon4);
            switchToGame();
        });

        menuStart.getChildren().addAll(btnStart, btnOptions, btnExit, iconSound, iconSoundMute);
        instruction.getChildren().addAll(btnInstruction, btnBack);
        pause.getChildren().addAll(btnResume, btnNewGame, btnQuit);
        icon.getChildren().add(iconSound);
        icon2.getChildren().add(iconSoundMute);
        icon3.getChildren().add(iconSound1);
        icon4.getChildren().add(iconSoundMute1);
        getChildren().addAll(menuStart, icon);
    }

    public void switchToGame() {
        game.start();
        Bomberman.setRoot(game.getRoot());
        Bomberman.setInputHandlers(game.getInputHandlers());
    }

    public void ressume() {
        game.stop();
    }
}
