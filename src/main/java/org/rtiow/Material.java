package org.rtiow;

public class Material {
    public float schlick(float cosine, float ref_idx) {
        float r0 = (1-ref_idx) / (1+ref_idx);
        r0 = r0 * r0;
        return r0 + (1-r0)*(float)Math.pow((1 - cosine), 5);
    }
    boolean refract(Vec3 v, Vec3 n, float ni_over_nt, Vec3 refracted) {
        Vec3 uv = v.unit_vector();
        float dt = uv.dot(n);
        float discriminant = 1.0f - ni_over_nt*ni_over_nt*(1-dt*dt);
        if (discriminant > 0) {
            refracted.set(uv.sub(n.mult(dt)).mult(ni_over_nt).sub(n.mult((float)Math.sqrt(discriminant))));
            return true;
        } else {
            return false;
        }
    }
    Vec3 reflect(Vec3 v, Vec3 n) {
        return v.sub(n.mult(2*v.dot(n)));
    }
    Vec3 random_in_unit_sphere() {
        Vec3 p;
        do {
            p = new Vec3((float)Math.random(), (float)Math.random(), (float)Math.random()).mult(2).sub(new Vec3(1,1,1));
        } while (p.squared_length() >= 1.0);
        return p;
    }
    boolean scatter(Ray r_in, HitRecord rec, Vec3 attenuation, Ray scattered) {
        return false;
    }
}
