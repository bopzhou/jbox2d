package org.jbox2d.structs.collision;

import org.jbox2d.common.Sweep;
import org.jbox2d.structs.collision.distance.DistanceProxy;

/**
 * Input parameters for TOI
 * @author Daniel Murphy
 */
public class TOIInput {
	public final DistanceProxy proxyA = new DistanceProxy();
	public final DistanceProxy proxyB = new DistanceProxy();
	public Sweep sweepA;
	public Sweep sweepB;
	/**
	 * defines sweep interval [0, tMax]
	 */
	public float tMax;
}