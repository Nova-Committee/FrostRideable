package committee.nova.frostrideable.mixin;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.FrostWalkerEnchantment;
import org.spongepowered.asm.mixin.Mixin;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@Mixin(FrostWalkerEnchantment.class)
public abstract class MixinFrostWalkerEnchantment extends Enchantment {
    protected MixinFrostWalkerEnchantment(Rarity r, EnchantmentCategory c, EquipmentSlot[] s) {
        super(r, c, s);
    }

    @Override
    public Map<EquipmentSlot, ItemStack> getSlotItems(LivingEntity entity) {
        final Map<EquipmentSlot, ItemStack> items = super.getSlotItems(entity);
        if (!(entity instanceof AbstractHorse horse)) return items;
        final ItemStack armor = horse.getItemBySlot(EquipmentSlot.CHEST);
        if (!armor.isEmpty()) items.put(EquipmentSlot.CHEST, armor);
        return items;
    }
}
