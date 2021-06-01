package de.oszimt.ls.aliendefence.view.menue;

import de.oszimt.ls.aliendefence.controller.AlienDefenceController;
import de.oszimt.ls.aliendefence.controller.LevelController;
import de.oszimt.ls.aliendefence.model.Level;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LevelChoice {
    private JPanel panel;
    private JButton btnNewLevel;
    private JButton btnUpdateLevel;
    private JTable tblLevels;
    private JButton btnDeleteLevel;

    private final LevelController lvlControl;
    private final LeveldesignWindow leveldesignWindow;
    private DefaultTableModel jTableData;

    /**
     * Create the panel
     * @param controller
     * @param leveldesignWindow
     */
    public LevelChoice(AlienDefenceController controller, LeveldesignWindow leveldesignWindow) {
        this.lvlControl = controller.getLevelController();
        this.leveldesignWindow = leveldesignWindow;

        btnNewLevel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                btnNewLevel_Clicked();
            }
        });

        btnUpdateLevel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnUpdateLevel_Clicked();
            }
        });

        btnDeleteLevel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnDeleteLevel_Clicked();
            }
        });

        tblLevels.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.updateTableData();
    }

    private String[][] getLevelsAsTableModel() {
        List<Level> levels = this.lvlControl.readAllLevels();
        String[][] result = new String[levels.size()][];
        int i = 0;
        for (Level l : levels) {
            result[i++] = l.getData();
        }
        return result;
    }

    public void updateTableData() {
        this.jTableData = new DefaultTableModel(this.getLevelsAsTableModel(), Level.getLevelDescriptions());
        this.tblLevels.setModel(jTableData);
    }

    public void btnNewLevel_Clicked() {
        this.leveldesignWindow.startLevelEditor();
    }

    public void btnUpdateLevel_Clicked() {
        int level_id = Integer
                .parseInt((String) this.tblLevels.getModel().getValueAt(this.tblLevels.getSelectedRow(), 0));
        this.leveldesignWindow.startLevelEditor(level_id);
    }

    public void btnDeleteLevel_Clicked() {
        int level_id = Integer
                .parseInt((String) this.tblLevels.getModel().getValueAt(this.tblLevels.getSelectedRow(), 0));
        this.lvlControl.deleteLevel(level_id);
        this.updateTableData();
    }

    public JPanel getPanel() {
        return panel;
    }
}
