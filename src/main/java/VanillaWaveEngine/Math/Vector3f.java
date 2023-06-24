package VanillaWaveEngine.Math;

public class Vector3f {

    public float x, y, z;

    public Vector3f() {

        this.x = x;
        this.y = y;
        this.z = z;

    }

    public Vector3f(float x, float y, float z) {

        this.x = x;
        this.y = y;
        this.z = z;

    }

    public void set(float x, float y, float z) {

        this.x = x;
        this.y = y;
        this.z = z;

    }

    public static Vector3f add(Vector3f vector1, Vector3f vector2){

        return new Vector3f(vector1.getX() + vector2.getX(), vector1.getY() + vector2.getY(), vector1.getZ() + vector2.getZ());

    }

    public static Vector3f subtract(Vector3f vector1, Vector3f vector2){

        return new Vector3f(vector1.getX() - vector2.getX(), vector1.getY() - vector2.getY(), vector1.getZ() - vector2.getZ());

    }

    public static Vector3f multiply(Vector3f vector1, Vector3f vector2){

        return new Vector3f(vector1.getX() * vector2.getX(), vector1.getY() * vector2.getY(), vector1.getZ() * vector2.getZ());

    }

    public static Vector3f divide(Vector3f vector1, Vector3f vector2){

        return new Vector3f(vector1.getX() / vector2.getX(), vector1.getY() / vector2.getY(), vector1.getZ() / vector2.getZ());

    }

    public static float vectorLength(Vector3f vector) {

        return (float) Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY() + vector.getZ() * vector.getZ());

    }

    public static Vector3f normalize(Vector3f vector) {

        float vectorLength = Vector3f.vectorLength(vector);
        return Vector3f.divide(vector, new Vector3f(vectorLength, vectorLength, vectorLength));

    }

    public static float dot(Vector3f vector1, Vector3f vector2) {

        return vector1.getX() * vector2.getX() + vector1.getY() * vector2.getY() + vector1.getZ() * vector2.getZ();

    }

    public float getX() {

        return x;

    }

    public void setX(float x) {

        this.x = x;

    }

    public float getY() {

        return y;

    }

    public void setY(float y) {

        this.y = y;

    }

    public float getZ() {

        return z;

    }

    public void setZ(float z) {

        this.z = z;

    }

}
