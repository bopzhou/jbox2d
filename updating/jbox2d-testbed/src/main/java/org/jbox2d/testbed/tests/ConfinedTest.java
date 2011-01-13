package org.jbox2d.testbed.tests;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.testbed.framework.TestbedSettings;
import org.jbox2d.testbed.framework.TestbedTest;

public class ConfinedTest extends TestbedTest {

	int e_columnCount = 0;
	int e_rowCount = 0;
	
	@Override
	public String getTestName() {
		return "Confined Test";
	}

	@Override
	public void initTest() {
		{
			BodyDef bd = new BodyDef();
			Body ground = world.createBody(bd);

			PolygonShape shape = new PolygonShape();

			// Floor
			shape.setAsEdge(new Vec2(-10.0f, 0.0f), new Vec2(10.0f, 0.0f));
			ground.createFixture(shape, 0.0f);

			// Left wall
			shape.setAsEdge(new Vec2(-10.0f, 0.0f), new Vec2(-10.0f, 20.0f));
			ground.createFixture(shape, 0.0f);

			// Right wall
			shape.setAsEdge(new Vec2(10.0f, 0.0f), new Vec2(10.0f, 20.0f));
			ground.createFixture(shape, 0.0f);

			// Roof
			shape.setAsEdge(new Vec2(-10.0f, 20.0f), new Vec2(10.0f, 20.0f));
			ground.createFixture(shape, 0.0f);
		}

		float radius = 0.5f;
		CircleShape shape = new CircleShape();
		shape.m_p.setZero();
		shape.m_radius = radius;

		FixtureDef fd = new FixtureDef();
		fd.shape = shape;
		fd.density = 1.0f;
		fd.friction = 0.1f;

		for (int j = 0; j < e_columnCount; ++j)
		{
			for (int i = 0; i < e_rowCount; ++i)
			{
				BodyDef bd = new BodyDef();
				bd.type = BodyType.DYNAMIC;
				bd.position.set(-10.0f + (2.1f * j + 1.0f + 0.01f * i) * radius, (2.0f * i + 1.0f) * radius);
				Body body = world.createBody(bd);

				body.createFixture(fd);
			}
		}

		world.setGravity(new Vec2(0.0f, 0.0f));
	}
	
	public void createCircle()
	{
		float radius = 2.0f;
		CircleShape shape = new CircleShape();
		shape.m_p.setZero();
		shape.m_radius = radius;

		FixtureDef fd = new FixtureDef();
		fd.shape = shape;
		fd.density = 1.0f;
		fd.friction = 0.0f;

		Vec2 p = new Vec2((float)Math.random(), 3.0f + (float)Math.random());
		BodyDef bd = new BodyDef();
		bd.type = BodyType.DYNAMIC;
		bd.position = p;
		//bd.allowSleep = false;
		Body body = world.createBody(bd);

		body.createFixture(fd);
	}
	
	@Override
	public void step(TestbedSettings settings) {

		super.step(settings);

		for (Body b = world.getBodyList(); b != null; b = b.getNext())
		{
			if (b.getType() != BodyType.DYNAMIC)
			{
				continue;
			}

			Vec2 p = b.getPosition();
			if (p.x <= -10.0f || 10.0f <= p.x || p.y <= 0.0f || 20.0f <= p.y)
			{
				p.x += 0.0;
			}
		}

		addTextLine("Press 'c' to create a circle");
	}

	@Override
	public void keyPressed(char argKeyChar, int argKeyCode) {
		switch(argKeyChar){
		case 'c':
			createCircle();
			break;
		}
	}
}