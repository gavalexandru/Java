package org.example;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.*;

public class Game extends Application {

    private Pane drawingPanel;
    private ObservableList<Circle> dots = FXCollections.observableArrayList();
    private ObservableList<Line> lines = FXCollections.observableArrayList();
    private int numDots = 10;
    private Circle firstSelectedDot = null;
    private Line previewLine = null;
    private boolean isPlayer1Turn = true;
    private Label scoreLabel;
    private Label optimalScoreLabel;
    private Label turnLabel;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));


        HBox configPanel = createConfigPanel();
        root.setTop(configPanel);


        drawingPanel = new Pane();
        drawingPanel.setStyle("-fx-background-color: white; -fx-border-color: #ccc;");
        drawingPanel.setPadding(new Insets(10));
        root.setCenter(drawingPanel);


        HBox controlPanel = createControlPanel();
        root.setBottom(controlPanel);

        drawDots();


        Scene scene = new Scene(root, 1000, 500);
        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createConfigPanel() {
        HBox panel = new HBox(10);
        panel.setPadding(new Insets(10));
        panel.setAlignment(Pos.CENTER_LEFT);
        panel.setStyle("-fx-background-color: #e0e0e0;");

        Label dotsLabel = new Label("Number of dots:");
        Spinner<Integer> dotsSpinner = new Spinner<>(3, 100, numDots);
        dotsSpinner.setEditable(true);

        Button newGameBtn = new Button("New Game");
        newGameBtn.setOnAction(e -> {
            numDots = dotsSpinner.getValue();
            resetGame();
            drawDots();
            updateScores();
        });


        Separator separator1 = new Separator();
        separator1.setOrientation(javafx.geometry.Orientation.VERTICAL);


        turnLabel = new Label();
        turnLabel.textProperty().bind(
                Bindings.when(new SimpleBooleanProperty(isPlayer1Turn))
                        .then("Current: Player 1 (Blue)")
                        .otherwise("Current: Player 2 (Red)"));


        Separator separator2 = new Separator();
        separator2.setOrientation(javafx.geometry.Orientation.VERTICAL);


        HBox colorIndicators = new HBox(5);
        colorIndicators.setAlignment(Pos.CENTER_LEFT);
        Circle player1Color = new Circle(5, Color.BLUE);
        Circle player2Color = new Circle(5, Color.RED);
        colorIndicators.getChildren().addAll(
                new Label("Player 1:"), player1Color,
                new Label("Player 2:"), player2Color
        );


        Separator separator3 = new Separator();
        separator3.setOrientation(javafx.geometry.Orientation.VERTICAL);


        scoreLabel = new Label("Player 1: 0 | Player 2: 0");
        optimalScoreLabel = new Label("Optimal: 0");

        panel.getChildren().addAll(
                dotsLabel, dotsSpinner, newGameBtn,
                separator1,
                turnLabel,
                separator2,
                colorIndicators,
                separator3,
                scoreLabel,
                new Label(" | "),
                optimalScoreLabel
        );


        HBox.setHgrow(panel, Priority.ALWAYS);
        return panel;
    }

    private HBox createControlPanel() {
        HBox panel = new HBox(10);
        panel.setPadding(new Insets(10));
        panel.setAlignment(Pos.CENTER);
        panel.setStyle("-fx-background-color: #e0e0e0; -fx-border-color: #ccc; -fx-border-width: 1 0 0 0;");


        Button loadBtn = new Button("Load");
        Button saveBtn = new Button("Save");
        Button exitBtn = new Button("Exit");


        loadBtn.setOnAction(e -> {});
        saveBtn.setOnAction(e -> {});
        exitBtn.setOnAction(e -> System.exit(0));

        panel.getChildren().addAll(loadBtn, saveBtn, exitBtn);
        return panel;
    }

    private void resetGame() {
        drawingPanel.getChildren().clear();
        dots.clear();
        lines.clear();
        firstSelectedDot = null;
        isPlayer1Turn = true;
        if (previewLine != null) {
            drawingPanel.getChildren().remove(previewLine);
            previewLine = null;
        }
    }

    private void drawDots() {
        Random rand = new Random();
        double dotRadius = 4;
        double panelWidth = drawingPanel.getWidth();
        double panelHeight = drawingPanel.getHeight();


        if (panelWidth <= 0) panelWidth = 600;
        if (panelHeight <= 0) panelHeight = 400;

        for (int i = 0; i < numDots; i++) {
            double x = dotRadius + rand.nextDouble() * (panelWidth - 2 * dotRadius);
            double y = dotRadius + rand.nextDouble() * (panelHeight - 2 * dotRadius);

            Circle dot = new Circle(x, y, dotRadius, Color.BLACK);
            dot.setUserData(i);

            dot.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    handleDotClick(dot);
                }
            });

            dot.setOnMousePressed(e -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    firstSelectedDot = dot;
                    previewLine = new Line(
                            dot.getCenterX(), dot.getCenterY(),
                            e.getX(), e.getY()
                    );
                    previewLine.setStroke(isPlayer1Turn ? Color.BLUE : Color.RED);
                    previewLine.setStrokeWidth(2);
                    drawingPanel.getChildren().add(previewLine);
                }
            });

            dot.setOnMouseDragged(e -> {
                if (previewLine != null) {
                    previewLine.setEndX(e.getX());
                    previewLine.setEndY(e.getY());
                }
            });

            dot.setOnMouseReleased(e -> {
                if (previewLine != null) {
                    drawingPanel.getChildren().remove(previewLine);
                    previewLine = null;

                    Circle secondDot = findDotAt(e.getX(), e.getY());
                    if (secondDot != null && secondDot != firstSelectedDot) {
                        createLine(firstSelectedDot, secondDot);
                    }
                    firstSelectedDot = null;
                }
            });

            dots.add(dot);
            drawingPanel.getChildren().add(dot);
        }
    }

    private Circle findDotAt(double x, double y) {
        for (Circle dot : dots) {
            double dx = x - dot.getCenterX();
            double dy = y - dot.getCenterY();
            double distance = Math.sqrt(dx * dx + dy * dy);
            if (distance <= dot.getRadius()) {
                return dot;
            }
        }
        return null;
    }

    private void handleDotClick(Circle dot) {
        if (firstSelectedDot == null) {
            firstSelectedDot = dot;
            dot.setFill(isPlayer1Turn ? Color.LIGHTBLUE : Color.PINK);
        } else {
            if (firstSelectedDot != dot) {
                createLine(firstSelectedDot, dot);
            }
            firstSelectedDot.setFill(Color.BLACK);
            firstSelectedDot = null;
        }
    }

    private void createLine(Circle startDot, Circle endDot) {

        for (Line line : lines) {
            if ((line.getStartX() == startDot.getCenterX() && line.getStartY() == startDot.getCenterY() &&
                    line.getEndX() == endDot.getCenterX() && line.getEndY() == endDot.getCenterY()) ||
                    (line.getStartX() == endDot.getCenterX() && line.getStartY() == endDot.getCenterY() &&
                            line.getEndX() == startDot.getCenterX() && line.getEndY() == startDot.getCenterY())) {
                return;
            }
        }

        Line line = new Line(
                startDot.getCenterX(), startDot.getCenterY(),
                endDot.getCenterX(), endDot.getCenterY()
        );
        line.setStroke(isPlayer1Turn ? Color.BLUE : Color.RED);
        line.setStrokeWidth(2);

        lines.add(line);
        drawingPanel.getChildren().add(line);

        isPlayer1Turn = !isPlayer1Turn;
        updateScores();
    }

    private void updateScores() {
        double player1Score = 0;
        double player2Score = 0;

        for (Line line : lines) {
            double length = calculateLineLength(line);
            if (line.getStroke().equals(Color.BLUE)) {
                player1Score += length;
            } else {
                player2Score += length;
            }
        }

        scoreLabel.setText(String.format("Player 1: %.2f | Player 2: %.2f", player1Score, player2Score));


        double optimalScore = calculateOptimalScore();
        optimalScoreLabel.setText(String.format("Optimal: %.2f", optimalScore));
    }

    private double calculateLineLength(Line line) {
        double dx = line.getEndX() - line.getStartX();
        double dy = line.getEndY() - line.getStartY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    private double calculateOptimalScore() {
        if (dots.size() < 2) return 0;


        List<Edge> edges = new ArrayList<>();
        List<Circle> dotList = new ArrayList<>(dots);


        for (int i = 0; i < dotList.size(); i++) {
            for (int j = i + 1; j < dotList.size(); j++) {
                Circle a = dotList.get(i);
                Circle b = dotList.get(j);
                double length = Math.sqrt(Math.pow(a.getCenterX() - b.getCenterX(), 2) +
                        Math.pow(a.getCenterY() - b.getCenterY(), 2));
                edges.add(new Edge(i, j, length));
            }
        }


        edges.sort(Comparator.comparingDouble(e -> e.length));


        int[] parent = new int[dotList.size()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        double totalLength = 0;
        int edgesAdded = 0;

        for (Edge edge : edges) {
            int rootA = find(parent, edge.a);
            int rootB = find(parent, edge.b);

            if (rootA != rootB) {
                union(parent, rootA, rootB);
                totalLength += edge.length;
                edgesAdded++;

                if (edgesAdded == dotList.size() - 1) {
                    break;
                }
            }
        }

        return totalLength;
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    private void union(int[] parent, int x, int y) {
        parent[find(parent, x)] = find(parent, y);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static class Edge {
        int a, b;
        double length;

        public Edge(int a, int b, double length) {
            this.a = a;
            this.b = b;
            this.length = length;
        }
    }
}