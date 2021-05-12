package eu.tsp.evilwanderingtrader.client.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import eu.tsp.evilwanderingtrader.common.entities.GypsyEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class GypsyModel<T extends GypsyEntity> extends EntityModel<T> {

	private final ModelRenderer head;
	private final ModelRenderer nose;
	private final ModelRenderer body;
	private final ModelRenderer arms;
	private final ModelRenderer mirrored;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer bodywear;
	private final ModelRenderer headwear;
	private final ModelRenderer headwear2;
	private final ModelRenderer rotation;


	public GypsyModel() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 24.0F, 0.0F);
		head.setTextureOffset(0, 0).addBox(-4.0F, -34.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.0F, false);

		nose = new ModelRenderer(this);
		nose.setRotationPoint(0.0F, 24.0F, 0.0F);
		nose.setTextureOffset(24, 0).addBox(-1.0F, -27.0F, -6.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(16, 20).addBox(-4.0F, -24.0F, -3.0F, 8.0F, 12.0F, 6.0F, 0.0F, false);

		arms = new ModelRenderer(this);
		arms.setRotationPoint(0.0F, 24.0F, 0.0F);
		arms.setTextureOffset(40, 38).addBox(-4.0F, -20.0F, -2.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		arms.setTextureOffset(44, 22).addBox(-8.0F, -24.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		mirrored = new ModelRenderer(this);
		mirrored.setRotationPoint(0.0F, 0.0F, 0.0F);
		arms.addChild(mirrored);
		mirrored.setTextureOffset(44, 22).addBox(4.0F, -24.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, true);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(0.0F, 24.0F, 0.0F);
		left_leg.setTextureOffset(0, 22).addBox(-4.0F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(0.0F, 24.0F, 0.0F);
		right_leg.setTextureOffset(0, 22).addBox(0.0F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		bodywear = new ModelRenderer(this);
		bodywear.setRotationPoint(0.0F, 24.0F, 0.0F);
		bodywear.setTextureOffset(0, 38).addBox(-4.0F, -24.0F, -3.0F, 8.0F, 18.0F, 6.0F, 0.5F, false);

		headwear = new ModelRenderer(this);
		headwear.setRotationPoint(0.0F, 24.0F, 0.0F);
		headwear.setTextureOffset(32, 0).addBox(-4.0F, -34.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.25F, false);

		headwear2 = new ModelRenderer(this);
		headwear2.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		rotation = new ModelRenderer(this);
		rotation.setRotationPoint(0.0F, 0.0F, 0.0F);
		headwear2.addChild(rotation);
		rotation.setTextureOffset(30, 47).addBox(-8.0F, -32.0F, -6.0F, 16.0F, 16.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		nose.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		arms.render(matrixStack, buffer, packedLight, packedOverlay);
		left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		bodywear.render(matrixStack, buffer, packedLight, packedOverlay);
		headwear.render(matrixStack, buffer, packedLight, packedOverlay);
		headwear2.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		// TODO Auto-generated method stub
		
	}
	
}
