package org.bukkitcontrib.gui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

public interface Widget{
	
	public int getNumBytes();
	
	public WidgetType getType();
	
	public UUID getId();
	
	public void render();
	
	public void readData(DataInputStream input) throws IOException;
	
	public void writeData(DataOutputStream output) throws IOException;
	
	public void setDirty(boolean dirty);
	
	public boolean isDirty();
	
	public RenderPriority getPriority();
	
	public Widget setPriority(RenderPriority priority);
	
	public int getWidth();
	
	public Widget setWidth(int width);
	
	public int getHeight();
	
	public Widget setHeight(int heigh);
	
	public Screen getScreen();
	
	public Widget setScreen(Screen screen);

	public int getUpperRightX();
	
	public int getUpperRightY();
	
	public Widget setUpperRightX(int pos);
	
	public Widget setUpperRightY(int pos);
	
	public Widget shiftXPos(int x);
	
	public Widget shiftYPos(int y);
	
	public boolean isVisible();
	
	public Widget setVisible(boolean enable);
}
