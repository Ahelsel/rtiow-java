package org.rtiow;

public class Lambertian extends Material {
    Vec3 albedo;
    public Lambertian(Vec3 a) {
        albedo = a;
    }
    public boolean scatter(Ray r_in, HitRecord rec, Vec3 attenuation, Ray scattered) {
        Vec3 target = rec.p.add(rec.normal).add(random_in_unit_sphere());
        scattered = new Ray(rec.p, target.sub(rec.p));
        attenuation.set(albedo);
        return true;
    }

}
