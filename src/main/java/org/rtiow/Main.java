package org.rtiow;
import java.util.ArrayList;
import java.util.List;

// https://github.com/rogerallen/raytracinginoneweekendincuda/blob/master/main.cc

public class Main {
    public static final Random rand = new Random();
    public double drand48() {
        return rand.nextDouble();
    }

    public Vec3 color(Ray r, List<Hittable> world, int depth) {
        HitRecord rec = new HitRecord();
        if (world.hit(r, 0.001F, Float.MAX_VALUE, rec)) {
            Ray scattered = new Ray();
            Vec3 attenuation = new Vec3();
            if (depth < 50 && rec.mat.scatter(r, rec, attenuation, scattered)) {
                return attenuation.mul(color(scattered, world, depth+1));
            } else {
                return new Vec3(0F,0F,0F);
            }
        } else {
            Vec3 unit_direction = Vec3.unitVector(r.direction());
            float t = 0.5F * (unit_direction.y() + 1.0F);
            return Vec3.add(Vec3.mul(1.0F-t, new Vec3(1.0F, 1.0F, 1.0F)), Vec3.mul(t, new Vec3(0.5F, 0.7F, 1.0F)));
        }
    }
    public List<Hittable> randomScene() {
        List<Hittable> list = new ArrayList<>();
        list.add(new Sphere(new Vec3(0F,-1000F,0F), 1000F, new Lambertian(new Vec3(0.5F, 0.5F, 0.5F)));
        for (int a = -11; a < 11; a++) {
            for (int b = -11; b < 11; b++) {
                float choose_mat = (float)Math.random();
                Vec3 center = new Vec3(a+0.9F*(float)Math.random(), 0.2F, b+0.9F*(float)Math.random());
                if (Vec3.sub(center, new Vec3(4F, 0.2F, 0F)).length() > 0.9F) {
                    if (choose_mat < 0.8F) {
                        list.add(new Sphere(center, 0.2F, new Lambertian(new Vec3((float)Math.random()*(float)Math.random(), (float)Math.random()*(float)Math.random(), (float)Math.random()*(float)Math.random()))));
                    } else if (choose_mat < 0.95F) {
                        list.add(new Sphere(center, 0.2F, new Metal(new Vec3(0.5F*(1+(float)Math.random()), 0.5F*(1+(float)Math.random()), 0.5F*(1+(float)Math.random())), 0.5F*(float)Math.random())));
                    } else {
                        list.add(new Sphere(center, 0.2F, new Dielectric(1.5F)));
                    }
                }
            }
        }
        list.add(new Sphere(new Vec3(0F, 1F, 0F), 1.0F, new Dielectric(1.5F)));
        list.add(new Sphere(new Vec3(-4F, 1F, 0F), 1.0F, new Lambertian(new Vec3(0.4F, 0.2F, 0.1F)));
        list.add(new Sphere(new Vec3(4F, 1F, 0F), 1.0F, new Metal(new Vec3(0.7F, 0.6F, 0.5F), 0.0F)));
        return list;
    }
    public static void main(String[] args) {
        // @TODO: Implement
        int nx = 1200;
        int ny = 800;
        int ns = 10;
        System.out.println("P3\n" + nx + " " + ny + "\n255\n");
        List<Hittable> list = new ArrayList<>();
        list.add(new Sphere(new Vec3(0F,0F,-1F), 0.5F, new Lambertian(new Vec3(0.1F, 0.2F,0.5F))));
        list.add(new Sphere(new Vec3(0F,-100.5F,-1F), 100F, new Lambertian(new Vec3(0.8F, 0.8F, 0.0F)));
        list.add(new Sphere(new Vec3(1F,0F,-1F), 0.5F, new Metal(new Vec3(0.8F, 0.6F, 0.2F), 0.0F)));
        list.add(new Sphere(new Vec3(-1F,0F,-1F), 0.5F, new Dielectric(1.5F)));
        list.add(new Sphere(new Vec3(-1F,0F,-1F), -0.45F, new Dielectric(1.5F)));

        HittableList world = new HittableList(list, 5);
        world = new HittableList(new Main().randomScene(), 5);

        Vec3 lookFrom = new Vec3(13F, 2F, 3F);
        Vec3 lookAt =  new Vec3(0F, 0F, 0F);
        float dist_to_focus = 10.0F;
        float aperture = 0.1F;

        Camera cam = new Camera(lookFrom, lookAt, new Vec3(0F,1F,0F), 20F, (float)nx/(float)ny, aperture, dist_to_focus);

        for (int j = ny-1; j >= 0; j--) {
            for (int i = 0; i < nx; i++) {
                Vec3 col = new Vec3(0F, 0F, 0F);
                for (int s = 0; s < ns; s++) {
                    float u = (float)(i + drand48()) / (float)nx;
                    float v = (float)(j + drand48()) / (float)ny;
                    Ray r = cam.getRay(u, v);
                    col = Vec3.add(col, new Main().color(r, world, 0));
                }
                col = Vec3.div(col, (float)ns);
                col = new Vec3((float)Math.sqrt(col.x()), (float)Math.sqrt(col.y()), (float)Math.sqrt(col.z()));
                int ir = int(255.99*col.x());
                int ig = int(255.99*col.y());
                int ib = int(255.99*col.z());
                System.out.println(ir + " " + ig + " " + ib);
            }
        }
    }
}