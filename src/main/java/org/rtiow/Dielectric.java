package org.rtiow;
import java.util.Random;

public class Dielectric extends Material {
    private final Random rand = new Random();
    float ref_idx;
    public Dielectric(float ri) {
        ref_idx = ri;
    }
    private double drand48() {
        return rand.nextDouble();
    }
    boolean scatter(Ray r_in, HitRecord rec, Vec3 attenuation, Ray scattered) {
        Vec3 outward_normal;
        Vec3 reflected = reflect(r_in.direction(), rec.normal);
        float ni_over_nt;
        attenuation.set(new Vec3(1, 1, 1));
        Vec3 refracted = new Vec3();
        float reflect_prob;
        float cosine;
        if (r_in.direction().dot(rec.normal) > 0) {
            outward_normal = rec.normal.mult(-1);
            ni_over_nt = ref_idx;
            cosine = r_in.direction().dot(rec.normal) / r_in.direction().length();
            cosine = (float)Math.sqrt(1 - ref_idx*ref_idx*(1-cosine*cosine));
        } else {
            outward_normal = rec.normal;
            ni_over_nt = 1.0f / ref_idx;
            cosine = -(r_in.direction().dot(rec.normal)) / r_in.direction().length();
        }
        if (refract(r_in.direction(), outward_normal, ni_over_nt, refracted)) {
            reflect_prob = schlick(cosine, ref_idx);
        } else {
            reflect_prob = (float)1.0;
        }
        if (drand48() < reflect_prob) {
            scattered = new Ray(rec.p, reflected);
        } else {
            scattered = new Ray(rec.p, refracted);
        }
        return true;
    }
}
