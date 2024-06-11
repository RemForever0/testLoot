package net.seyarada.mythicloot;

import java.util.Random;

import net.seyarada.mythicloot.nms.*;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import io.lumine.xikage.mythicmobs.utils.Schedulers;
import io.lumine.xikage.mythicmobs.utils.terminable.Terminable;
import net.seyarada.mythicloot.rank.Util;

public class ISchedulers implements Terminable {

	Util u = new Util();
	Color rgb;
	
	public void explodeParticles(Item item, Player p, String color, double expheight, double expoffset) {

		String fColor = u.getColor(item, color);
		rgb = u.getRGB(fColor);
		
		Random random = new Random();
		Vector velocity = new Vector(
				Math.cos(random.nextDouble() * 3.141592653589793D * 2.0D) * expoffset, expheight,
				Math.sin(random.nextDouble() * 3.141592653589793D * 2.0D) * expoffset);

		item.setVelocity(velocity);
		
	}
	
	public void keepInvisible(Item item, String player) {

		Schedulers.sync().runRepeating(() -> this.kISchedueler(item, player), 0, 40);
	}
	
	public void kISchedueler(Item item, String player) {
		if (item.isValid()) {
        	for (Entity entity : item.getNearbyEntities(24, 24, 24)){
        		if (entity instanceof Player && !entity.getName().equals(player)) {
        			
        			String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

					switch (version) {
						case "v1_12_R1":
							new V1_12_R1().destroyEntity(item, entity);
							break;
					}
        			
		         }
		    }
        }
        else {
        	this.terminate();
        }
	}

	@Override
	public void close() {
	}

	
}
