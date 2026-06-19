package com.rafaelauler.bedwars;

import org.bukkit.Location;

public class GeneratorManager {

    
	private void createDisplay(
	        Generator generator,
	        int id) {

	    GeneratorDisplay display =
	            GeneratorDisplayFactory.create(
	                    generator,
	                    "generator_" + id
	            );

	    generator.setDisplay(display);

	    new FloatingItemTask(
	            display.getStand()
	    ).runTaskTimer(
	            Bedwars.getInstance(),
	            1L,
	            1L
	    );
	}
	public Generator getClosestGenerator(
	        Arena arena,
	        Location location) {

	    Generator closest = null;

	    double distance =
	            Double.MAX_VALUE;

	    for(Generator generator :
	            arena.getGenerators()) {

	        double current =
	                generator.getLocation()
	                        .distance(location);

	        if(current < distance) {

	            distance = current;
	            closest = generator;
	        }
	    }

	    return closest;
	}
	public void stop(Arena arena) {

	    for(Generator generator :
	            arena.getGenerators()) {

	        if(generator.getDisplay()
	                == null)
	            continue;

	        generator.getDisplay()
	                .getStand()
	                .remove();

	        generator.getDisplay()
	                .getHologram()
	                .delete();
	    }
	}
	public void start(Arena arena) {

	    int id = 0;

	    for(Generator generator :
	            arena.getGenerators()) {

	        createDisplay(
	                generator,
	                id++
	        );

	        switch(generator.getType()) {

	            case DIAMOND:

	                new DiamondGeneratorTask(
	                        generator,
	                        30
	                ).runTaskTimer(
	                        Bedwars.getInstance(),
	                        20L,
	                        20L
	                );

	                break;

	            case EMERALD:

	                new EmeraldGeneratorTask(
	                        generator,
	                        60
	                ).runTaskTimer(
	                        Bedwars.getInstance(),
	                        20L,
	                        20L
	                );

	                break;
	        }
	    }
	}
}
