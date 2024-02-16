package org.rtiow;

public class Metal extends Material {
    Vec3 albedo;
    float fuzz;
    public Metal(Vec3 a, float f) {
        albedo = a;
        if (f < 1) {
            fuzz = f;
        } else {
            fuzz = 1;
        }
    }
    boolean scatter(Ray r_in, HitRecord rec, Vec3 attenuation, Ray scattered) {
        Vec3 reflected = reflect(r_in.direction().unit_vector(), rec.normal);
        scattered = new Ray(rec.p, reflected.add(random_in_unit_sphere().mult(fuzz)));
        attenuation.set(albedo);
        return scattered.direction().dot(rec.normal) > 0;
    }

}
