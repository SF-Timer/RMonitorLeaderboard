package com.zacharyfox.rmonitor.leaderboard;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.Enumeration;

import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;

import com.zacharyfox.rmonitor.leaderboard.frames.ConnectFrame;
import com.zacharyfox.rmonitor.leaderboard.frames.MainFrame;

public class LeaderBoard
{
	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		float scale=1f;
		UIManager.LookAndFeelInfo looks[] = UIManager.getInstalledLookAndFeels();

		for (UIManager.LookAndFeelInfo info : looks) {

		    //if you want to change LaF to a spesific LaF,such as "GTK"
		    //put here a if statement like:
		    //if(info.getClassName().contains("GTK"))
		    UIManager.setLookAndFeel(info.getClassName());

		    UIDefaults defaults = UIManager.getDefaults();
		    Enumeration<Object> newKeys = defaults.keys();

		    while (newKeys.hasMoreElements()) {
		        Object obj = newKeys.nextElement();
		        Object current = UIManager.get(obj);
		        if (current instanceof FontUIResource) {
		            FontUIResource resource = (FontUIResource) current;
		            defaults.put(obj, new FontUIResource(resource.deriveFont(resource.getSize2D()*scale)));
		            // System.out.printf("%50s : %s\n", obj,  UIManager.get(obj));
		        } else if (current instanceof Font) {
		            Font resource = (Font) current;
		            defaults.put(obj, resource.deriveFont(resource.getSize2D()*scale));
		            // System.out.printf("%50s : %s\n", obj,  UIManager.get(obj));
		        }
		    }
		}
		
		try {
			System.setProperty("apple.awt.fullscreenhidecursor","true");
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			System.setProperty("com.apple.mrj.application.apple.menu.about.name", "RMonitorLeaderboard");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run()
			{
				try {
					MainFrame window = new MainFrame();
					window.setVisible(true);
					ConnectFrame newFrame = ConnectFrame.getInstance(window);
					newFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
