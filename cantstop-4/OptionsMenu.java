import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;


// popup options menu
public class OptionsMenu extends JFrame{
    
    private JButton saveGame, loadGame;
    private JPanel optionsPanel;
    private JFileChooser fileChooser;
    private StopBoard parent;
    private Writer writer;
    private String gameData;

    public OptionsMenu(StopBoard parent){
        
        this.parent = parent;
        this.setSize(600,400);
        optionsPanel = new JPanel();
        this.add(optionsPanel);
        optionsPanel.setLayout(new FlowLayout());
        saveGame = new JButton("Save Game");
        saveGame.addActionListener(e -> {
            saveGame();
        });
        loadGame = new JButton("Load Game");
        loadGame.addActionListener(e -> {
            gameData = loadGame();
            parent.loadGameData(gameData);
        });
        optionsPanel.add(saveGame);
        optionsPanel.add(loadGame);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setVisible(true);
    }

    public String getData(){
        return gameData;
    }

    public void saveGame(){
        fileChooser = new JFileChooser();
        File saveDir = new File("~/Documents/");
        fileChooser.setCurrentDirectory(saveDir);
        if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
            try{
                File saveFile = fileChooser.getSelectedFile();

                FileWriter writer = new FileWriter(saveFile);
                writer.write(parent.passData());
                writer.close();
                JOptionPane.showMessageDialog(parent, "File creation successful!");
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(parent, "There was an error saving the file!");
            }
        }
    }
    public String loadGame(){
        fileChooser = new JFileChooser();
        File loadDir = new File("~/Documents/");
        fileChooser.setCurrentDirectory(loadDir);
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            try{
                File loadFile = fileChooser.getSelectedFile();
                Path path = Path.of(loadFile.getAbsolutePath());
                return Files.readString(path);
            }
            catch(Exception e){
                System.out.println("File not found error");
            }
        }
        else{
            System.out.println("Operation aborted");
        }
        return "";
    }


    
}

