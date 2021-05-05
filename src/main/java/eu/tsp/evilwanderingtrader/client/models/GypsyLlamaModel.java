package eu.tsp.evilwanderingtrader.client.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import eu.tsp.evilwanderingtrader.common.entities.GypsyLlama;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class GypsyLlamaModel<T extends GypsyLlama> extends EntityModel<T> {

	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer body_rotation;
	private final ModelRenderer body_rotation_r1;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer chest_left;
	private final ModelRenderer chest_left_rotation;
	private final ModelRenderer chest_left_rotation_r1;
	private final ModelRenderer chest_right;
	private final ModelRenderer chest_right_rotation;

	public GypsyLlamaModel() {
		textureWidth = 128;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 24.0F, 0.0F);
		head.setTextureOffset(0, 0).addBox(-2.0F, -31.0F, -16.0F, 4.0F, 4.0F, 9.0F, 0.0F, false);
		head.setTextureOffset(0, 14).addBox(-4.0F, -33.0F, -12.0F, 8.0F, 18.0F, 6.0F, 0.0F, false);
		head.setTextureOffset(17, 0).addBox(1.0F, -36.0F, -10.0F, 3.0F, 3.0F, 2.0F, 0.0F, true);
		head.setTextureOffset(17, 0).addBox(-4.0F, -36.0F, -10.0F, 3.0F, 3.0F, 2.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		body_rotation = new ModelRenderer(this);
		body_rotation.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(body_rotation);
		

		body_rotation_r1 = new ModelRenderer(this);
		body_rotation_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		body_rotation.addChild(body_rotation_r1);
		setRotationAngle(body_rotation_r1, 1.5708F, 0.0F, 0.0F);
		body_rotation_r1.setTextureOffset(29, 0).addBox(-6.0F, -8.0F, 12.0F, 12.0F, 18.0F, 10.0F, 0.0F, false);

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(0.0F, 24.0F, 0.0F);
		leg1.setTextureOffset(29, 29).addBox(1.5F, -14.0F, 4.0F, 4.0F, 14.0F, 4.0F, 0.0F, true);

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(0.0F, 24.0F, 0.0F);
		leg2.setTextureOffset(29, 29).addBox(-5.5F, -14.0F, 4.0F, 4.0F, 14.0F, 4.0F, 0.0F, false);

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(0.0F, 24.0F, 0.0F);
		leg3.setTextureOffset(29, 29).addBox(1.5F, -14.0F, -7.0F, 4.0F, 14.0F, 4.0F, 0.0F, true);

		leg4 = new ModelRenderer(this);
		leg4.setRotationPoint(0.0F, 24.0F, 0.0F);
		leg4.setTextureOffset(29, 29).addBox(-5.5F, -14.0F, -7.0F, 4.0F, 14.0F, 4.0F, 0.0F, false);

		chest_left = new ModelRenderer(this);
		chest_left.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		chest_left_rotation = new ModelRenderer(this);
		chest_left_rotation.setRotationPoint(0.0F, 0.0F, 0.0F);
		chest_left.addChild(chest_left_rotation);
		

		chest_left_rotation_r1 = new ModelRenderer(this);
		chest_left_rotation_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		chest_left_rotation.addChild(chest_left_rotation_r1);
		setRotationAngle(chest_left_rotation_r1, 0.0F, 1.5708F, 0.0F);
		chest_left_rotation_r1.setTextureOffset(45, 41).addBox(-6.0F, -21.0F, 6.0F, 8.0F, 8.0F, 3.0F, 0.0F, false);

		chest_right = new ModelRenderer(this);
		chest_right.setRotationPoint(-7.0F, 24.0F, -5.0F);
		setRotationAngle(chest_right, 0.0F, 1.5708F, 0.0F);
		

		chest_right_rotation = new ModelRenderer(this);
		chest_right_rotation.setRotationPoint(0.0F, 0.0F, 0.0F);
		chest_right.addChild(chest_right_rotation);
		chest_right_rotation.setTextureOffset(45, 28).addBox(-11.0F, -21.0F, -2.0F, 8.0F, 8.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		leg1.render(matrixStack, buffer, packedLight, packedOverlay);
		leg2.render(matrixStack, buffer, packedLight, packedOverlay);
		leg3.render(matrixStack, buffer, packedLight, packedOverlay);
		leg4.render(matrixStack, buffer, packedLight, packedOverlay);
		chest_left.render(matrixStack, buffer, packedLight, packedOverlay);
		chest_right.render(matrixStack, buffer, packedLight, packedOverlay);
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
