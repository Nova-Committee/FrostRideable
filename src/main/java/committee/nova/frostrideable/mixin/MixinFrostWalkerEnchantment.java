package committee.nova.frostrideable.mixin;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@Mixin(FrostWalkerEnchantment.class)
public abstract class MixinFrostWalkerEnchantment extends Enchantment {
    protected MixinFrostWalkerEnchantment(Rarity r, EnchantmentType t, EquipmentSlotType[] s) {
        super(r, t, s);
    }

    @Override
    public Map<EquipmentSlotType, ItemStack> getSlotItems(LivingEntity entity) {
        final Map<EquipmentSlotType, ItemStack> items = super.getSlotItems(entity);
        if (!(entity instanceof AbstractHorseEntity)) return items;
        final AbstractHorseEntity horse = (AbstractHorseEntity) entity;
        final ItemStack armor = horse.getItemBySlot(EquipmentSlotType.CHEST);
        if (!armor.isEmpty()) items.put(EquipmentSlotType.CHEST, armor);
        return items;
    }
}
