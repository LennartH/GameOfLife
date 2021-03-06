package org.gameoflife.frontend.swing.impl;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.gameoflife.backend.shared.CellState;
import org.gameoflife.backend.shared.dto.CellDTO;
import org.gameoflife.backend.shared.impl.dto.SimpleCellDTO;
import org.gameoflife.controller.GameController;
import org.gameoflife.frontend.swing.CellRenderer;

public class PanelCellRenderer extends AbstractProvidesComponent implements CellRenderer {
	
	private static final Color BORDER_COLOR = Color.DARK_GRAY;
	private static final Map<CellState, Color> STATE_COLORS = new HashMap<>(); {
		STATE_COLORS.put(CellState.ALIVE, Color.GREEN);
	}

    private GameController gameController;
	
	private final JPanel panel;
	
	private CellState state;
    
    public PanelCellRenderer(CellState initialState, GameController gameController) {
        this.gameController = gameController;
        
        panel = new JPanel();
        panel.addMouseListener(new MouseReleasedListener() {
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                toggleState();
            }
        });
        
        Border cellBorder = BorderFactory.createLineBorder(BORDER_COLOR, 1);
        panel.setBorder(cellBorder);
        
        setState(initialState);
    }
	
    private void toggleState() {
        if (isEditable()) {
            setState(getNextState());
        }
    }

    private CellState getNextState() {
        int nextStateIndex = getState().ordinal() + 1;
        if (nextStateIndex >= CellState.values().length) {
            nextStateIndex = 0;
        }
        return CellState.values()[nextStateIndex];
    }
	
	@Override
	public CellState getState() {
		return state;
	}

	@Override
	public void setState(CellState state) {
		this.state = state;
		panel.setBackground(getColorFor(state));
	}

	private Color getColorFor(CellState state) {
		return STATE_COLORS.get(state);
	}
	
	public boolean isEditable() {
        return !gameController.isGameStarted();
    }
	
	@Override
	public CellDTO getCellDTO() {
	    return new SimpleCellDTO(getState());
	}

	@Override
    public Component getComponent() {
        return panel;
    }

}
