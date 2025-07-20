package user_steam;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SteamProfileViewer extends Application {

    private final SteamProfileService steamService = new SteamProfileService();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Visualizador de Perfis Steam");

        // Elementos da interface
        TextField steamIdField = new TextField();
        steamIdField.setPromptText("Digite o Steam ID");

        Button searchButton = new Button("Buscar");

        Label nameLabel = new Label("Nome: ");
        Label stateLabel = new Label("Status: ");
        Label urlLabel = new Label("Perfil: ");
        ImageView avatarView = new ImageView();
        avatarView.setFitWidth(64);
        avatarView.setFitHeight(64);

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        // Evento de busca
        searchButton.setOnAction(event -> {
                String input = steamIdField.getText().trim();
                if (input.isEmpty()) {
                    errorLabel.setText("Por favor, insira um Steam ID ou nome personalizado.");
                    return;
                }

                String steamId = input;

                // Detecta se é nome personalizado (não numérico)
                if (!steamId.matches("\\d+")) {
                    steamId = steamService.resolveVanityURL(input);
                    if (steamId == null) {
                        errorLabel.setText("Usuário não encontrado.");
                        return;
                    }
                }

                try {
                    steamService.setId(steamId);

                    String nome = steamService.getRealName();
                    String estado = steamService.getState();
                    String url = steamService.getProfileURL();
                    String avatarUrl = steamService.getAvatar();

                    nameLabel.setText("Nome: " + nome);
                    stateLabel.setText("Status: " + estado);
                    urlLabel.setText("Perfil: " + url);
                    avatarView.setImage(new Image(avatarUrl));

                    errorLabel.setText("");

                } catch (Exception e) {
                    errorLabel.setText("Erro: " + e.getMessage());
                    e.printStackTrace();
                }
        });

        VBox root = new VBox(10, steamIdField, searchButton, avatarView, nameLabel, stateLabel, urlLabel, errorLabel);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
