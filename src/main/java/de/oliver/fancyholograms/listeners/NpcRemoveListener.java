package de.oliver.fancyholograms.listeners;

import de.oliver.fancyholograms.FancyHolograms;
import de.oliver.fancyholograms.Hologram;
import de.oliver.fancynpcs.Npc;
import de.oliver.fancynpcs.events.NpcRemoveEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NpcRemoveListener implements Listener {

    @EventHandler
    public void onNpcRemove(NpcRemoveEvent event){
        Npc npc = event.getNpc();

        for (Hologram hologram : FancyHolograms.getInstance().getHologramManager().getAllHolograms()) {
            if(hologram.getLinkedNpc() != null && hologram.getLinkedNpc() == npc){
                hologram.setLinkedNpc(null);
            }
        }
    }

}