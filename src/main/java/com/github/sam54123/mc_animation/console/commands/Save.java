package com.github.sam54123.mc_animation.console.commands;

import java.io.IOException;

import com.github.sam54123.mc_animation.console.CommandBase;
import com.github.sam54123.mc_animation.console.Console;
import com.github.sam54123.mc_animation.utils.MCAnimStatics;

public class Save extends CommandBase {

	@Override
	protected boolean onRun(Console console, String[] args) {
		if (console.loadedAnim == null) {
			console.out.println("No animation loaded!");
			return false;
		}
		
		String path;
		
		// Set path to the passed path if present, otherwise set it to the one in the animation
		if (args.length >= 1) {
			
			path = args[0];
			
			String extention;
			try
			{
				 extention = path.substring(path.lastIndexOf("."));
			}
			catch(IndexOutOfBoundsException e) 
			{
				 extention = "noExtention";
			}
			
			if (!extention.matches(".mcanim"))
			{
				path = MCAnimStatics.formatPath(path)+console.loadedAnim.name+".mcanim";
			}
			
		} else {
			path = console.loadedAnim.path;
		}
		
		// Try to save the animation
		try {
			console.loadedAnim.save(path);
			return true;
		} catch(IOException e) {
			console.out.println("File failed to save...");
			return false;
		}
	}

	@Override
	public String getName() {
		return "save";
	}

	@Override
	public String getUsage() {
		return "save <path>";
	}

	@Override
	public String getDescription() {
		return "Saves the current animation to a .mcanim file";
	}

}
