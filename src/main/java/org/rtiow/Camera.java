package org.rtiow;

public class Camera {
    Vec3 origin;
    Vec3 lower_left_corner;
    Vec3 horizontal;
    Vec3 vertical;
    Vec3 u, v, w;
    float lens_radius;

    public Camera(Vec3 lookFrom, Vec3 lookAt, Vec3 vup, float vfov, float aspect, float aperture, float focus_dist) { // vfov is top to bottom in degrees
        lens_radius = aperture / 2;
        float theta = (float) (vfov * Math.PI / 180);
        float half_height = (float) Math.tan(theta / 2);
        float half_width = aspect * half_height;
        origin = lookFrom;
        w = lookFrom.sub(lookAt).unit_vector();
        u = vup.cross(w).unit_vector();
        v = w.cross(u);
        lower_left_corner = origin.sub(u.mult(half_width * focus_dist)).sub(v.mult(half_height * focus_dist)).sub(w.mult(focus_dist));
        horizontal = u.mult(2 * half_width * focus_dist);
        vertical = v.mult(2 * half_height * focus_dist);
    }

    public Ray get_ray(float u, float v) {
        Vec3 rd = Vec3.random_in_unit_disk().mult(lens_radius);
        Vec3 offset = this.u.mult(rd.x()).add(this.v.mult(rd.y()));
        return new Ray(origin.add(offset), lower_left_corner.add(horizontal.mult(u)).add(vertical.mult(v)).sub(origin).sub(offset));
    }

}
