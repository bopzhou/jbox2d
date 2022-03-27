package org.jbox2d.testbed.tests;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.EdgeShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.joints.WheelJoint;
import org.jbox2d.dynamics.joints.WheelJointDef;
import org.jbox2d.testbed.framework.TestbedSettings;
import org.jbox2d.testbed.framework.TestbedTest;

public class MyTest extends TestbedTest {

    private static final long CAR_TAG = 100l;
    private static final long WHEEL1_TAG = 101l;
    private static final long WHEEL2_TAG = 102l;
    private static final long SPRING1_TAG = 103l;
    private static final long SPRING2_TAG = 104l;

    private float m_hz;
    private float m_zeta;

    private Body m_ship1;
    private Body m_wheel1;
    private Body m_wheel2;

    private float m_speed;
    private WheelJoint m_spring1;
    private WheelJoint m_spring2;


    private Body m_ship2;
    private Body m_wheel12;
    private Body m_wheel22;

    private float m_speed2;
    private WheelJoint m_spring12;
    private WheelJoint m_spring22;

    @Override
    public void initTest(boolean deserialized) {
        if (deserialized) {
            return;
        }
        m_hz = 4.0f;
        m_zeta = 0.7f;
        m_speed = 50.0f;

        m_speed2 = 50.0f;

        Body ground = null;
        {
            BodyDef bd = new BodyDef();
            ground = m_world.createBody(bd);
            EdgeShape shape = new EdgeShape();

            FixtureDef fd = new FixtureDef();
            fd.shape = shape;
            fd.density = 0.0f;
            fd.friction = 0.6f;

            shape.set(new Vec2(3.0f, 0.0f), new Vec2(3.0f, 20.0f));
            ground.createFixture(fd);

            shape.set(new Vec2(3.0f, 0.0f), new Vec2(59.0f, 0.0f));
            ground.createFixture(fd);

            shape.set(new Vec2(59f, 0.0f), new Vec2(59f, 20.0f));
            ground.createFixture(fd);
        }

        // Ship
        {
            PolygonShape chassis = new PolygonShape();
            Vec2 vertices[] = new Vec2[8];
            vertices[0] = new Vec2(-1.5f, 0f);
            vertices[1] = new Vec2(1.5f, 0f);
            vertices[2] = new Vec2(1.5f, 2f);
            vertices[3] = new Vec2(-1.5f, 2f);
            chassis.set(vertices, 4);

            float offset = 8f;

            BodyDef bd = new BodyDef();
            bd.type = BodyType.DYNAMIC;
            bd.position.set(0.0f + offset, 0f);
            m_ship1 = m_world.createBody(bd);
            m_ship1.createFixture(chassis, 1.0f);

            // set velocity
            m_ship1.setLinearVelocity(new Vec2(20, 0));
            // set restitution coe
            m_ship1.m_fixtureList.m_restitution = 0.3f;
        }


        // Ship2
        {
            PolygonShape chassis = new PolygonShape();
            Vec2 vertices[] = new Vec2[8];
            vertices[0] = new Vec2(-1.5f, 0f);
            vertices[1] = new Vec2(1.5f, 0f);
            vertices[2] = new Vec2(1.5f, 2f);
            vertices[3] = new Vec2(-1.5f, 2f);
            chassis.set(vertices, 4);

            float offset = 54f;

            BodyDef bd = new BodyDef();
            bd.type = BodyType.DYNAMIC;
            bd.position.set(0.0f + offset, 0f);
            m_ship2 = m_world.createBody(bd);
            m_ship2.createFixture(chassis, 1.0f);

            // set velocity
            m_ship2.setLinearVelocity(new Vec2(-20, 0));
            // set restitution coe
            m_ship2.m_fixtureList.m_restitution = 0.3f;
        }
    }

    // @Override
    public void initTest0(boolean deserialized) {
        if (deserialized) {
            return;
        }
        m_hz = 4.0f;
        m_zeta = 0.7f;
        m_speed = 50.0f;

        m_speed2 = 50.0f;

        Body ground = null;
        {
            BodyDef bd = new BodyDef();
            ground = m_world.createBody(bd);

            EdgeShape shape = new EdgeShape();

            FixtureDef fd = new FixtureDef();
            fd.shape = shape;
            fd.density = 0.0f;
            fd.friction = 0.6f;

            shape.set(new Vec2(0.0f, 0.0f), new Vec2(0.0f, 20.0f));
            ground.createFixture(fd);

            shape.set(new Vec2(0.0f, 0.0f), new Vec2(56.0f, 0.0f));
            ground.createFixture(fd);

            shape.set(new Vec2(56f, 0.0f), new Vec2(56f, 20.0f));
            ground.createFixture(fd);
        }

        // Car
        {
            PolygonShape chassis = new PolygonShape();
            Vec2 vertices[] = new Vec2[8];
            vertices[0] = new Vec2(-1.5f, -0.5f);
            vertices[1] = new Vec2(1.5f, -0.5f);
            vertices[2] = new Vec2(1.5f, 0.0f);
            vertices[3] = new Vec2(0.0f, 0.9f);
            vertices[4] = new Vec2(-1.15f, 0.9f);
            vertices[5] = new Vec2(-1.5f, 0.2f);
            chassis.set(vertices, 6);

            CircleShape circle = new CircleShape();
            circle.m_radius = 0.4f;

            float offset = 5f;

            BodyDef bd = new BodyDef();
            bd.type = BodyType.DYNAMIC;
            bd.position.set(0.0f + offset, 1.0f);
            m_ship1 = m_world.createBody(bd);
            m_ship1.createFixture(chassis, 1.0f);

            // set speed
            m_ship1.setLinearVelocity(new Vec2(20, 0));
            m_ship1.m_fixtureList.m_restitution = 0.3f;

            FixtureDef fd = new FixtureDef();
            fd.shape = circle;
            fd.density = 1.0f;
            fd.friction = 0.9f;

            bd.position.set(-1.0f + offset, 0.35f);
            m_wheel1 = m_world.createBody(bd);
            m_wheel1.createFixture(fd);

            bd.position.set(1.0f + offset, 0.4f);
            m_wheel2 = m_world.createBody(bd);
            m_wheel2.createFixture(fd);

            WheelJointDef jd = new WheelJointDef();
            Vec2 axis = new Vec2(0.0f, 1.0f);

            jd.initialize(m_ship1, m_wheel1, m_wheel1.getPosition(), axis);
            jd.motorSpeed = 0.0f;
            jd.maxMotorTorque = 20.0f;
            jd.enableMotor = true;
            jd.frequencyHz = m_hz;
            jd.dampingRatio = m_zeta;
            m_spring1 = (WheelJoint) m_world.createJoint(jd);

            jd.initialize(m_ship1, m_wheel2, m_wheel2.getPosition(), axis);
            jd.motorSpeed = 0.0f;
            jd.maxMotorTorque = 10.0f;
            jd.enableMotor = false;
            jd.frequencyHz = m_hz;
            jd.dampingRatio = m_zeta;
            m_spring2 = (WheelJoint) m_world.createJoint(jd);
        }


        // Car2
        {
            PolygonShape chassis = new PolygonShape();
            Vec2 vertices[] = new Vec2[8];
            vertices[0] = new Vec2(-1.5f, -0.5f);
            vertices[1] = new Vec2(1.5f, -0.5f);
            vertices[2] = new Vec2(1.5f, 0.2f);
            vertices[3] = new Vec2(1.15f, 0.9f);
            vertices[4] = new Vec2(0.0f, 0.9f);
            vertices[5] = new Vec2(-1.5f, 0.0f);
            chassis.set(vertices, 6);

//            vertices[0] = new Vec2(-1.5f, -0.5f);
//            vertices[1] = new Vec2(1.5f, -0.5f);
//            vertices[2] = new Vec2(1.5f, 0.9f);
//            vertices[3] = new Vec2(-1.5f, 0.9f);
//            chassis.set(vertices, 4);


            CircleShape circle = new CircleShape();
            circle.m_radius = 0.4f;

            float offset = 50f;

            BodyDef bd = new BodyDef();
            bd.type = BodyType.DYNAMIC;
            bd.position.set(0.0f + offset, 1.0f);
            m_ship2 = m_world.createBody(bd);
            m_ship2.createFixture(chassis, 1.0f);

            // set speed
            m_ship2.setLinearVelocity(new Vec2(-20, 0));
            m_ship2.m_fixtureList.m_restitution = 0.3f;

            FixtureDef fd = new FixtureDef();
            fd.shape = circle;
            fd.density = 1.0f;
            fd.friction = 0.3f;

            bd.position.set(1.0f + offset, 0.35f);
            m_wheel12 = m_world.createBody(bd);
            m_wheel12.createFixture(fd);

            bd.position.set(-1.0f + offset, 0.4f);
            m_wheel22 = m_world.createBody(bd);
            m_wheel22.createFixture(fd);

            WheelJointDef jd = new WheelJointDef();
            Vec2 axis = new Vec2(0.0f, -1.0f);

            jd.initialize(m_ship2, m_wheel12, m_wheel12.getPosition(), axis);
            jd.motorSpeed = 0.0f;
            jd.maxMotorTorque = 20.0f;
            jd.enableMotor = true;
            jd.frequencyHz = m_hz;
            jd.dampingRatio = m_zeta;
            m_spring12 = (WheelJoint) m_world.createJoint(jd);

            jd.initialize(m_ship2, m_wheel22, m_wheel22.getPosition(), axis);
            jd.motorSpeed = 0.0f;
            jd.maxMotorTorque = 10.0f;
            jd.enableMotor = false;
            jd.frequencyHz = m_hz;
            jd.dampingRatio = m_zeta;
            m_spring22 = (WheelJoint) m_world.createJoint(jd);
        }
    }

    @Override
    public String getTestName() {
        return "my test";
    }

    @Override
    public synchronized void step(TestbedSettings settings) {
        super.step(settings);
        addTextLine("Keys: left = a, brake = s, right = d, hz down = q, hz up = e");
        addTextLine("frequency = " + m_hz + " hz, damping ratio = " + m_zeta);

        // getCamera().setCamera(m_car2.getPosition());
        getCamera().setCamera(new Vec2(30f, 0f));
    }
}
