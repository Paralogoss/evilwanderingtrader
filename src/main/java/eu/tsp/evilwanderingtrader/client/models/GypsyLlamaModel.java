package eu.tsp.evilwanderingtrader.client.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import eu.tsp.evilwanderingtrader.common.entities.GypsyLlamaEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class GypsyLlamaModel<T extends GypsyLlamaEntity> extends EntityModel<T> {

    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer body_rotation;
    private final ModelRenderer body_rotation_r1;
    private final ModelRenderer legBackLeft;
    private final ModelRenderer legBackRight;
    private final ModelRenderer legFrontLeft;
    private final ModelRenderer legFrontRight;
    private final ModelRenderer chest_left;
    private final ModelRenderer chest_left_rotation;
    private final ModelRenderer chest_left_rotation_r1;
    private final ModelRenderer chest_right;
    private final ModelRenderer chest_right_rotation;

    public GypsyLlamaModel() {
        textureWidth = 128;
        textureHeight = 64;

        head = new ModelRenderer(this);
        this.head.addBox(-2.0F, -14.0F, -10.0F, 4.0F, 4.0F, 9.0F);
        this.head.setRotationPoint(0.0F, 7.0F, -6.0F);
        this.head.setTextureOffset(0, 14).addBox(-4.0F, -16.0F, -6.0F, 8.0F, 18.0F, 6.0F);
        this.head.setTextureOffset(17, 0).addBox(-4.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F);
        this.head.setTextureOffset(17, 0).addBox(1.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, true);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 24.0F, 0.0F);
        body_rotation = new ModelRenderer(this);
        body_rotation.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.addChild(body_rotation);
        body_rotation_r1 = new ModelRenderer(this);
        body_rotation_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        body_rotation.addChild(body_rotation_r1);
        body_rotation_r1.rotateAngleX = 1.5708F;
        body_rotation_r1.setTextureOffset(29, 0).addBox(-6.0F, -8.0F, 12.0F, 12.0F, 18.0F, 10.0F);

        this.legBackRight = new ModelRenderer(this, 29, 29);
        this.legBackRight.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F);
        this.legBackRight.setRotationPoint(-1.5F, 10.0F, 6.0F);
        this.legBackLeft = new ModelRenderer(this, 29, 29);
        this.legBackLeft.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F, true);
        this.legBackLeft.setRotationPoint(3.5F, 10.0F, 6.0F);
        this.legFrontRight = new ModelRenderer(this, 29, 29);
        this.legFrontRight.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F);
        this.legFrontRight.setRotationPoint(-1.5F, 10.0F, -3.0F);
        this.legFrontLeft = new ModelRenderer(this, 29, 29);
        this.legFrontLeft.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F, true);
        this.legFrontLeft.setRotationPoint(3.5F, 10.0F, -3.0F);

        chest_left = new ModelRenderer(this);
        chest_left.setRotationPoint(0.0F, 24.0F, 0.0F);
        chest_left_rotation = new ModelRenderer(this);
        chest_left_rotation.setRotationPoint(0.0F, 0.0F, 0.0F);
        chest_left.addChild(chest_left_rotation);
        chest_left_rotation_r1 = new ModelRenderer(this);
        chest_left_rotation_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        chest_left_rotation.addChild(chest_left_rotation_r1);
        chest_left_rotation_r1.rotateAngleY = 1.5708F;
        chest_left_rotation_r1.setTextureOffset(45, 41).addBox(-6.0F, -21.0F, 6.0F, 8.0F, 8.0F, 3.0F);

        chest_right = new ModelRenderer(this);
        chest_right.setRotationPoint(-7.0F, 24.0F, -5.0F);
        chest_right.rotateAngleY = 1.5708F;
        chest_right_rotation = new ModelRenderer(this);
        chest_right_rotation.setRotationPoint(0.0F, 0.0F, 0.0F);
        chest_right.addChild(chest_right_rotation);
        chest_right_rotation.setTextureOffset(45, 28).addBox(-11.0F, -21.0F, -2.0F, 8.0F, 8.0F, 3.0F);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        legBackLeft.render(matrixStack, buffer, packedLight, packedOverlay);
        legBackRight.render(matrixStack, buffer, packedLight, packedOverlay);
        legFrontLeft.render(matrixStack, buffer, packedLight, packedOverlay);
        legFrontRight.render(matrixStack, buffer, packedLight, packedOverlay);
        chest_left.render(matrixStack, buffer, packedLight, packedOverlay);
        chest_right.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.rotateAngleX = headPitch * ((float) Math.PI / 180F);
        this.head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);

        this.legBackRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.25F * limbSwingAmount;
        this.legBackLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.25F * limbSwingAmount;
        this.legFrontRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.25F * limbSwingAmount;
        this.legFrontLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.25F * limbSwingAmount;
    }

}
