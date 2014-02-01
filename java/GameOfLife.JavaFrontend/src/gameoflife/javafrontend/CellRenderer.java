package gameoflife.javafrontend;

import org.gameoflife.backend.shared.CellState;
import org.gameoflife.backend.shared.dto.CellDTO;

public interface CellRenderer extends ProvidesComponent {

	public void setState(CellState state);
	public CellState getState();

    public void setEditable(boolean editable);
    boolean isEditable();
	
    public CellDTO getCellDTO();

}
