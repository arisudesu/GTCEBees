package dev.arisu.gtcebees;

import dev.arisu.gtcebees.items.GTCombs;
import forestry.api.core.ForestryAPI;
import forestry.core.items.IColoredItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        GTCombs.combItem.registerModel(GTCombs.combItem, ForestryAPI.modelManager);
    }

    public void preInit() {
        super.preInit();
    }

    public void postInit() {

        ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
        itemColors.registerItemColorHandler(ColoredItemItemColor.INSTANCE, GTCombs.combItem);
        super.postInit();
    }

    @SideOnly(Side.CLIENT)
    private static class ColoredItemItemColor implements IItemColor {
        public static final ClientProxy.ColoredItemItemColor INSTANCE = new ClientProxy.ColoredItemItemColor();

        private ColoredItemItemColor() {

        }

        @Override
        public int colorMultiplier(ItemStack stack, int tintIndex) {
            Item item = stack.getItem();
            if (item instanceof IColoredItem) {
                return ((IColoredItem) item).getColorFromItemstack(stack, tintIndex);
            }
            return 0xffffff;
        }
    }
}
