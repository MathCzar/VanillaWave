package Main;

import VanillaWaveEngine.Math.*;
import VanillaWaveEngine.Rendering.Mesh;
import VanillaWaveEngine.Rendering.TextItem;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.acos;

public class SphereMesh {

    private int resolution;

    private float piEnd = (float) Math.PI/4, piFrac = (float) Math.PI/2;

    private Mesh sphereMesh;

    private Vertex[] vertices = new Vertex[] {};
    private Vertex[] textCoords = new Vertex[] {};
    private float[] normals = new float[] {0, 0, 1};
    private int[] indices = new int[] {};

    public void createMesh(int resolution) {

        this.resolution = resolution;
        constructMesh(resolution);

    }

    public void constructMesh(int resolution) {

        //create/clean templates to add elements to the arrays
        vertices = new Vertex[] {};
        textCoords = new Vertex[] {};
        indices = new int[] {};

        //DecimalFormat sphereDecimalFormat = new DecimalFormat("#.#######");
        //sphereDecimalFormat.setRoundingMode(RoundingMode.CEILING);

        resolution -= 1;

        int numSquares = (int) Math.pow(2, resolution);

        for (int i = 0; i <= resolution; i++) {
            numSquares += (int) Math.pow(2, i);
        }

        // +x side
        //for (int y = 0; y < pointsPerRow; y++) {
//
        //    for (int x = 0; x < pointsPerRow; x++) {
//
        //        //System.out.println(x);
        //        //System.out.println(y);
//
        //        float fx = (float) x/(pointsPerRow - 1);
        //        float fy = (float) y/(pointsPerRow - 1);
//
        //        float fx2 = (float) fx * 2;
        //        float fy2 = (float) fy * 2;
//
        //        float sx = (float) fx2 - 1.0f;
        //        float sy = (float) fy2 - 1.0f;
        //        float sz = (float) 1.0f;
//
        //        float psx = (float) Math.pow(sx, 2);
        //        float psy = (float) Math.pow(sy, 2);
        //        float psz = (float) Math.pow(sz, 2);
//
        //        float hsx = (float) psx/2;
        //        float hsy = (float) psy/2;
        //        float hsz = (float) psz/2;
//
        //        float msi = (float) psy * psz;
        //        float msii = (float) psz * psx;
        //        float msiii = (float) psx * psy;
//
        //        float tsi = (float) msi/3;
        //        float tsii = (float) msii/3;
        //        float tsiii = (float) msiii/3;
//
        //        float psqi = (float) 1 - hsy - hsz + tsi;
        //        float psqii = (float) 1 - hsz - hsx + tsii;
        //        float psqiii = (float) 1 - hsx - hsy + tsiii;
//
        //        float sqi = (float) Math.sqrt(psqi);
        //        float sqii = (float) Math.sqrt(psqii);
        //        float sqiii = (float) Math.sqrt(psqiii);
//
        //        float i = (float) sx * sqi;
        //        float ii = (float) sy * sqii;
        //        float iii = (float) sz * sqiii;
//
        //        //System.out.println("(" + fx + "," + fy + ")");
        //        //System.out.println("(" + sx + "," + sy + "," + sz + ")");
        //        //System.out.println("(" + psx + "," + psy + "," + psz + ")");
        //        //System.out.println("(" + hsx + "," + hsy + "," + hsz + ")");
        //        //System.out.println("(" + msi + "," + msii + "," + msiii + ")");
        //        //System.out.println("(" + tsi + "," + tsii + "," + tsiii + ")");
        //        //System.out.println("(" + sqi + "," + sqii + "," + sqiii + ")");
        //        System.out.println("new Vertex(new Vector3f(" + i + "f ," + ii + "f ," + iii + "f )),");
//
        //        vertices = addVectorToArray(vertices, new Vertex(new Vector3f(i, ii, iii)));
//
//
//
        //    }
        //    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.0f, 0.0f)));
        //    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.0f, 1.0f)));
        //    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f( 1.0f, 1.0f)));
        //    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f( 1.0f, 0.0f)));
        //}

        // -x side
        //for (int y = 0; y < pointsPerRow; y++) {
//
        //    for (int x = 0; x < pointsPerRow; x++) {
//
        //        //System.out.println(x);
        //        //System.out.println(y);
//
        //        float fx = (float) x/(pointsPerRow - 1);
        //        float fy = (float) y/(pointsPerRow - 1);
//
        //        float fx2 = (float) fx * 2;
        //        float fy2 = (float) fy * 2;
//
        //        float sx = (float) fx2 - 1.0f;
        //        float sy = (float) fy2 - 1.0f;
        //        float sz = (float) -1.0f;
//
        //        float psx = (float) Math.pow(sx, 2);
        //        float psy = (float) Math.pow(sy, 2);
        //        float psz = (float) Math.pow(sz, 2);
//
        //        float hsx = (float) psx/2;
        //        float hsy = (float) psy/2;
        //        float hsz = (float) psz/2;
//
        //        float msi = (float) psy * psz;
        //        float msii = (float) psz * psx;
        //        float msiii = (float) psx * psy;
//
        //        float tsi = (float) msi/3;
        //        float tsii = (float) msii/3;
        //        float tsiii = (float) msiii/3;
//
        //        float psqi = (float) 1 - hsy - hsz + tsi;
        //        float psqii = (float) 1 - hsz - hsx + tsii;
        //        float psqiii = (float) 1 - hsx - hsy + tsiii;
//
        //        float sqi = (float) Math.sqrt(psqi);
        //        float sqii = (float) Math.sqrt(psqii);
        //        float sqiii = (float) Math.sqrt(psqiii);
//
        //        float i = (float) sx * sqi;
        //        float ii = (float) sy * sqii;
        //        float iii = (float) sz * sqiii;
//
        //        //System.out.println("(" + fx + "," + fy + ")");
        //        //System.out.println("(" + sx + "," + sy + "," + sz + ")");
        //        //System.out.println("(" + psx + "," + psy + "," + psz + ")");
        //        //System.out.println("(" + hsx + "," + hsy + "," + hsz + ")");
        //        //System.out.println("(" + msi + "," + msii + "," + msiii + ")");
        //        //System.out.println("(" + tsi + "," + tsii + "," + tsiii + ")");
        //        //System.out.println("(" + sqi + "," + sqii + "," + sqiii + ")");
        //        //System.out.println("(" + i + "," + ii + "," + iii + ")");
//
        //        vertices = addVectorToArray(vertices, new Vertex(new Vector3f(i, ii, iii)));
//
        //    }
//
        //}

        // +y side
        //for (int y = 0; y < pointsPerRow; y++) {
//
        //    for (int x = 0; x < pointsPerRow; x++) {
//
        //        //System.out.println(x);
        //        //System.out.println(y);
//
        //        float fx = (float) x/(pointsPerRow - 1);
        //        float fy = (float) y/(pointsPerRow - 1);
//
        //        float fx2 = (float) fx * 2;
        //        float fy2 = (float) fy * 2;
//
        //        float sx = (float) fx2 - 1.0f;
        //        float sy = (float) 1.0f;
        //        float sz = (float) fy2 - 1.0f;
//
        //        float psx = (float) Math.pow(sx, 2);
        //        float psy = (float) Math.pow(sy, 2);
        //        float psz = (float) Math.pow(sz, 2);
//
        //        float hsx = (float) psx/2;
        //        float hsy = (float) psy/2;
        //        float hsz = (float) psz/2;
//
        //        float msi = (float) psy * psz;
        //        float msii = (float) psz * psx;
        //        float msiii = (float) psx * psy;
//
        //        float tsi = (float) msi/3;
        //        float tsii = (float) msii/3;
        //        float tsiii = (float) msiii/3;
//
        //        float psqi = (float) 1 - hsy - hsz + tsi;
        //        float psqii = (float) 1 - hsz - hsx + tsii;
        //        float psqiii = (float) 1 - hsx - hsy + tsiii;
//
        //        float sqi = (float) Math.sqrt(psqi);
        //        float sqii = (float) Math.sqrt(psqii);
        //        float sqiii = (float) Math.sqrt(psqiii);
//
        //        float i = (float) sx * sqi;
        //        float ii = (float) sy * sqii;
        //        float iii = (float) sz * sqiii;
//
        //        //System.out.println("(" + fx + "," + fy + ")");
        //        //System.out.println("(" + sx + "," + sy + "," + sz + ")");
        //        //System.out.println("(" + psx + "," + psy + "," + psz + ")");
        //        //System.out.println("(" + hsx + "," + hsy + "," + hsz + ")");
        //        //System.out.println("(" + msi + "," + msii + "," + msiii + ")");
        //        //System.out.println("(" + tsi + "," + tsii + "," + tsiii + ")");
        //        //System.out.println("(" + sqi + "," + sqii + "," + sqiii + ")");
        //        //System.out.println("(" + i + "," + ii + "," + iii + ")");
//
        //        vertices = addVectorToArray(vertices, new Vertex(new Vector3f(i, ii, iii)));
//
        //    }
//
        //}

        // -y side
        //for (int y = 0; y < pointsPerRow; y++) {
//
        //    for (int x = 0; x < pointsPerRow; x++) {
//
        //        //System.out.println(x);
        //        //System.out.println(y);
//
        //        float fx = (float) x/(pointsPerRow - 1);
        //        float fy = (float) y/(pointsPerRow - 1);
//
        //        float fx2 = (float) fx * 2;
        //        float fy2 = (float) fy * 2;
//
        //        float sx = (float) fx2 - 1.0f;
        //        float sy = (float) -1.0f;
        //        float sz = (float) fy2 - 1.0f;
//
        //        float psx = (float) Math.pow(sx, 2);
        //        float psy = (float) Math.pow(sy, 2);
        //        float psz = (float) Math.pow(sz, 2);
//
        //        float hsx = (float) psx/2;
        //        float hsy = (float) psy/2;
        //        float hsz = (float) psz/2;
//
        //        float msi = (float) psy * psz;
        //        float msii = (float) psz * psx;
        //        float msiii = (float) psx * psy;
//
        //        float tsi = (float) msi/3;
        //        float tsii = (float) msii/3;
        //        float tsiii = (float) msiii/3;
//
        //        float psqi = (float) 1 - hsy - hsz + tsi;
        //        float psqii = (float) 1 - hsz - hsx + tsii;
        //        float psqiii = (float) 1 - hsx - hsy + tsiii;
//
        //        float sqi = (float) Math.sqrt(psqi);
        //        float sqii = (float) Math.sqrt(psqii);
        //        float sqiii = (float) Math.sqrt(psqiii);
//
        //        float i = (float) sx * sqi;
        //        float ii = (float) sy * sqii;
        //        float iii = (float) sz * sqiii;
//
        //        //System.out.println("(" + fx + "," + fy + ")");
        //        //System.out.println("(" + sx + "," + sy + "," + sz + ")");
        //        //System.out.println("(" + psx + "," + psy + "," + psz + ")");
        //        //System.out.println("(" + hsx + "," + hsy + "," + hsz + ")");
        //        //System.out.println("(" + msi + "," + msii + "," + msiii + ")");
        //        //System.out.println("(" + tsi + "," + tsii + "," + tsiii + ")");
        //        //System.out.println("(" + sqi + "," + sqii + "," + sqiii + ")");
        //        System.out.println("(" + i + "," + ii + "," + iii + ")");
//
        //        vertices = addVectorToArray(vertices, new Vertex(new Vector3f(i, ii, iii)));
//
        //    }
//
        //}

        // +z side
        //for (int y = 0; y < pointsPerRow; y++) {
//
        //    for (int x = 0; x < pointsPerRow; x++) {
//
        //        //System.out.println(x);
        //        //System.out.println(y);
//
        //        float fx = (float) x/(pointsPerRow - 1);
        //        float fy = (float) y/(pointsPerRow - 1);
//
        //        float fx2 = (float) fx * 2;
        //        float fy2 = (float) fy * 2;
//
        //        float sx = (float) 1.0f;
        //        float sy = (float) fx2 - 1.0f;
        //        float sz = (float) fy2 - 1.0f;
//
        //        float psx = (float) Math.pow(sx, 2);
        //        float psy = (float) Math.pow(sy, 2);
        //        float psz = (float) Math.pow(sz, 2);
//
        //        float hsx = (float) psx/2;
        //        float hsy = (float) psy/2;
        //        float hsz = (float) psz/2;
//
        //        float msi = (float) psy * psz;
        //        float msii = (float) psz * psx;
        //        float msiii = (float) psx * psy;
//
        //        float tsi = (float) msi/3;
        //        float tsii = (float) msii/3;
        //        float tsiii = (float) msiii/3;
//
        //        float psqi = (float) 1 - hsy - hsz + tsi;
        //        float psqii = (float) 1 - hsz - hsx + tsii;
        //        float psqiii = (float) 1 - hsx - hsy + tsiii;
//
        //        float sqi = (float) Math.sqrt(psqi);
        //        float sqii = (float) Math.sqrt(psqii);
        //        float sqiii = (float) Math.sqrt(psqiii);
//
        //        float i = (float) sx * sqi;
        //        float ii = (float) sy * sqii;
        //        float iii = (float) sz * sqiii;
//
        //        //System.out.println("(" + fx + "," + fy + ")");
        //        //System.out.println("(" + sx + "," + sy + "," + sz + ")");
        //        //System.out.println("(" + psx + "," + psy + "," + psz + ")");
        //        //System.out.println("(" + hsx + "," + hsy + "," + hsz + ")");
        //        //System.out.println("(" + msi + "," + msii + "," + msiii + ")");
        //        //System.out.println("(" + tsi + "," + tsii + "," + tsiii + ")");
        //        //System.out.println("(" + sqi + "," + sqii + "," + sqiii + ")");
        //        //System.out.println("(" + i + "," + ii + "," + iii + ")");
//
        //        vertices = addVectorToArray(vertices, new Vertex(new Vector3f(i, ii, iii)));
//
        //    }
//
        //}

        // -z side
        //for (int y = 0; y < pointsPerRow; y++) {
//
        //    for (int x = 0; x < pointsPerRow; x++) {
//
        //        //System.out.println(x);
        //        //System.out.println(y);
//
        //        float fx = (float) x/(pointsPerRow - 1);
        //        float fy = (float) y/(pointsPerRow - 1);
//
        //        float fx2 = (float) fx * 2;
        //        float fy2 = (float) fy * 2;
//
        //        float sx = (float) -1.0f;
        //        float sy = (float) fx2 - 1.0f;
        //        float sz = (float) fy2 - 1.0f;
//
        //        float psx = (float) Math.pow(sx, 2);
        //        float psy = (float) Math.pow(sy, 2);
        //        float psz = (float) Math.pow(sz, 2);
//
        //        float hsx = (float) psx/2;
        //        float hsy = (float) psy/2;
        //        float hsz = (float) psz/2;
//
        //        float msi = (float) psy * psz;
        //        float msii = (float) psz * psx;
        //        float msiii = (float) psx * psy;
//
        //        float tsi = (float) msi/3;
        //        float tsii = (float) msii/3;
        //        float tsiii = (float) msiii/3;
//
        //        float psqi = (float) 1 - hsy - hsz + tsi;
        //        float psqii = (float) 1 - hsz - hsx + tsii;
        //        float psqiii = (float) 1 - hsx - hsy + tsiii;
//
        //        float sqi = (float) Math.sqrt(psqi);
        //        float sqii = (float) Math.sqrt(psqii);
        //        float sqiii = (float) Math.sqrt(psqiii);
//
        //        float i = (float) sx * sqi;
        //        float ii = (float) sy * sqii;
        //        float iii = (float) sz * sqiii;
//
        //        //System.out.println("(" + fx + "," + fy + ")");
        //        //System.out.println("(" + sx + "," + sy + "," + sz + ")");
        //        //System.out.println("(" + psx + "," + psy + "," + psz + ")");
        //        //System.out.println("(" + hsx + "," + hsy + "," + hsz + ")");
        //        //System.out.println("(" + msi + "," + msii + "," + msiii + ")");
        //        //System.out.println("(" + tsi + "," + tsii + "," + tsiii + ")");
        //        //System.out.println("(" + sqi + "," + sqii + "," + sqiii + ")");
        //        //System.out.println("(" + i + "," + ii + "," + iii + ")");
//
        //        vertices = addVectorToArray(vertices, new Vertex(new Vector3f(i, ii, iii)));
//
        //    }
//
        //}


        float numSquaresFrac = piFrac/numSquares;

        System.out.println(numSquares);
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");

        // +X side
        for (int y = 0; y <= numSquares; y++) {

            for (int x = 0; x <= numSquares; x++) {

                float fractionX = numSquaresFrac * x;
                //System.out.println(fractionX);
                float fractionY = numSquaresFrac * y;
                //System.out.println(fractionY);

                float i = (float) ((Math.cos(-piEnd+(fractionY))) * (Math.cos(-piEnd+(fractionX))));
                float ii = (float) ((Math.cos(-piEnd+(fractionY))) * Math.sin(-piEnd+(fractionX)));
                float iii = (float) (Math.sin(-piEnd+(fractionY)));

                vertices = addVectorToArray(vertices, new Vertex(new Vector3f(i, ii, iii)));
                //addVectorToArray(vertices, new Vertex(new Vector3f((float) 0.49999997f, (float) -0.5f, (float) -0.70710677f)));
                //addVectorToArray(vertices, new Vertex(new Vector3f((float) 0.68301266f,(float) -0.18301271f,(float) -0.70710677f)));
                //addVectorToArray(vertices, new Vertex(new Vector3f((float) 0.68301266f,(float) -0.6830127f,(float) -0.25881904f)));

                //System.out.println(numSquares);
                //System.out.println(x + " " + y);
                System.out.println("(" + i + "," + ii + "," + iii + ")");
            }


        }
        System.out.println("out");
        // -X side
        for (int y = 0; y <= numSquares; y++) {

            for (int x = 0; x <= numSquares; x++) {

                float fractionX = numSquaresFrac * x;
                //System.out.println(fractionX);
                float fractionY = numSquaresFrac * y;
                //System.out.println(fractionY);

                float i = (float) ((Math.cos(-piEnd+(fractionY))) * (Math.cos(-piEnd+(fractionX))));
                float ii = (float) ((Math.cos(-piEnd+(fractionY))) * Math.sin(-piEnd+(fractionX)));
                float iii = (float) (Math.sin(-piEnd+(fractionY)));

                vertices = addVectorToArray(vertices, new Vertex(new Vector3f(-i, ii, iii)));
                //addVectorToArray(vertices, new Vertex(new Vector3f((float) 0.49999997f, (float) -0.5f, (float) -0.70710677f)));
                //addVectorToArray(vertices, new Vertex(new Vector3f((float) 0.68301266f,(float) -0.18301271f,(float) -0.70710677f)));
                //addVectorToArray(vertices, new Vertex(new Vector3f((float) 0.68301266f,(float) -0.6830127f,(float) -0.25881904f)));

                //System.out.println(numSquares);
                //System.out.println(x + " " + y);
                System.out.println("(" + -i + "," + ii + "," + iii + ")");
            }


        }
        System.out.println("out");
        // +Y side
        for (int y = 0; y <= numSquares; y++) {

            for (int x = 0; x <= numSquares; x++) {

                float fractionX = numSquaresFrac * x;
                //System.out.println(fractionX);
                float fractionY = numSquaresFrac * y;
                //System.out.println(fractionY);

                float i = (float) ((Math.cos(-piEnd+(fractionY))) * (Math.cos(-piEnd+(fractionX))));
                float ii = (float) ((Math.cos(-piEnd+(fractionY))) * Math.sin(-piEnd+(fractionX)));
                float iii = (float) (Math.sin(-piEnd+(fractionY)));

                vertices = addVectorToArray(vertices, new Vertex(new Vector3f(ii, i, iii)));
                //addVectorToArray(vertices, new Vertex(new Vector3f((float) 0.49999997f, (float) -0.5f, (float) -0.70710677f)));
                //addVectorToArray(vertices, new Vertex(new Vector3f((float) 0.68301266f,(float) -0.18301271f,(float) -0.70710677f)));
                //addVectorToArray(vertices, new Vertex(new Vector3f((float) 0.68301266f,(float) -0.6830127f,(float) -0.25881904f)));

                //System.out.println(numSquares);
                //System.out.println(x + " " + y);
                System.out.println("(" + ii + "," + i + "," + iii + ")");
            }


        }
        System.out.println("out");
        // -Y side
        for (int y = 0; y <= numSquares; y++) {

            for (int x = 0; x <= numSquares; x++) {

                float fractionX = numSquaresFrac * x;
                //System.out.println(fractionX);
                float fractionY = numSquaresFrac * y;
                //System.out.println(fractionY);

                float i = (float) ((Math.cos(-piEnd+(fractionY))) * (Math.cos(-piEnd+(fractionX))));
                float ii = (float) ((Math.cos(-piEnd+(fractionY))) * Math.sin(-piEnd+(fractionX)));
                float iii = (float) (Math.sin(-piEnd+(fractionY)));

                vertices = addVectorToArray(vertices, new Vertex(new Vector3f(ii, -i, iii)));
                //addVectorToArray(vertices, new Vertex(new Vector3f((float) 0.49999997f, (float) -0.5f, (float) -0.70710677f)));
                //addVectorToArray(vertices, new Vertex(new Vector3f((float) 0.68301266f,(float) -0.18301271f,(float) -0.70710677f)));
                //addVectorToArray(vertices, new Vertex(new Vector3f((float) 0.68301266f,(float) -0.6830127f,(float) -0.25881904f)));

                //System.out.println(numSquares);
                //System.out.println(x + " " + y);
                System.out.println("(" + ii+ "," + -i + "," + iii + ")");
            }


        }
        System.out.println("out");
        // +Z side
        for (int y = 0; y <= numSquares; y++) {

            for (int x = 0; x <= numSquares; x++) {

                float fractionX = numSquaresFrac * x;
                //System.out.println(fractionX);
                float fractionY = numSquaresFrac * y;
                //System.out.println(fractionY);

                float i = (float) ((Math.cos(-piEnd+(fractionY))) * (Math.cos(-piEnd+(fractionX))));
                float ii = (float) ((Math.cos(-piEnd+(fractionY))) * Math.sin(-piEnd+(fractionX)));
                float iii = (float) (Math.sin(-piEnd+(fractionY)));

                vertices = addVectorToArray(vertices, new Vertex(new Vector3f(iii, ii, i)));
                //addVectorToArray(vertices, new Vertex(new Vector3f((float) 0.49999997f, (float) -0.5f, (float) -0.70710677f)));
                //addVectorToArray(vertices, new Vertex(new Vector3f((float) 0.68301266f,(float) -0.18301271f,(float) -0.70710677f)));
                //addVectorToArray(vertices, new Vertex(new Vector3f((float) 0.68301266f,(float) -0.6830127f,(float) -0.25881904f)));

                //System.out.println(numSquares);
                //System.out.println(x + " " + y);
                System.out.println("(" + iii + "," + ii + "," + i + ")");
            }


        }
        System.out.println("out");
        // -Z side
        for (int y = 0; y <= numSquares; y++) {

            for (int x = 0; x <= numSquares; x++) {

                float fractionX = numSquaresFrac * x;
                //System.out.println(fractionX);
                float fractionY = numSquaresFrac * y;
                //System.out.println(fractionY);

                float i = (float) ((Math.cos(-piEnd+(fractionY))) * (Math.cos(-piEnd+(fractionX))));
                float ii = (float) ((Math.cos(-piEnd+(fractionY))) * Math.sin(-piEnd+(fractionX)));
                float iii = (float) (Math.sin(-piEnd+(fractionY)));

                vertices = addVectorToArray(vertices, new Vertex(new Vector3f(iii, ii, -i)));
                //addVectorToArray(vertices, new Vertex(new Vector3f((float) 0.49999997f, (float) -0.5f, (float) -0.70710677f)));
                //addVectorToArray(vertices, new Vertex(new Vector3f((float) 0.68301266f,(float) -0.18301271f,(float) -0.70710677f)));
                //addVectorToArray(vertices, new Vertex(new Vector3f((float) 0.68301266f,(float) -0.6830127f,(float) -0.25881904f)));

                //System.out.println(numSquares);
                //System.out.println(x + " " + y);
                System.out.println("(" + iii + "," + ii + "," + -i + ")");
            }


        }
        System.out.println("out");
        int totPoints = (((numSquares + 1) * (numSquares + 1))) * 6;

        //+x side
        //for (int y = 0; y < pointsPerRow - 1; y++) {
//
        //    for (int x = 0; x < pointsPerRow - 1; x++) {
//
        //        //System.out.println("(" + x + ", " + y + ")");
//
        //        System.out.println(x + (y * pointsPerRow));
        //        System.out.println(x + (y * pointsPerRow) + 1);
        //        System.out.println(x + (y * pointsPerRow) + pointsPerRow);
        //        System.out.println(x + (y * pointsPerRow) + pointsPerRow);
        //        System.out.println(x + (y * pointsPerRow) + pointsPerRow + 1);
        //        System.out.println(x + (y * pointsPerRow) + 1);
//
        //        indices = addIntToArray(indices, x + (y * pointsPerRow));
        //        //System.out.println(i-1);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + 1);
        //        //System.out.println(i);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + pointsPerRow);
        //        //System.out.println(i+numSquares);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + pointsPerRow);
        //        //System.out.println(i+numSquares);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + pointsPerRow + 1);
        //        //System.out.println(i+numSquares+1);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + 1);
        //        //System.out.println(i);
//
        //    }
        //}

        //-x side
        //for (int y = 0; y < pointsPerRow - 1; y++) {
//
        //    for (int x = 0; x < pointsPerRow - 1; x++) {
//
        //        //System.out.println("(" + x + ", " + y + ")");
////
        //        //System.out.println(x + (y * pointsPerRow));
        //        //System.out.println(x + (y * pointsPerRow) + 1);
        //        //System.out.println(x + (y * pointsPerRow) + pointsPerRow);
        //        //System.out.println(x + (y * pointsPerRow) + pointsPerRow);
        //        //System.out.println(x + (y * pointsPerRow) + pointsPerRow + 1);
        //        //System.out.println(x + (y * pointsPerRow) + 1);
//
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + (pointsPerRow * pointsPerRow));
        //        //System.out.println(i-1);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + 1 + (pointsPerRow * pointsPerRow));
        //        //System.out.println(i);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + pointsPerRow + (pointsPerRow * pointsPerRow));
        //        //System.out.println(i+numSquares);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + pointsPerRow + (pointsPerRow * pointsPerRow));
        //        //System.out.println(i+numSquares);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + pointsPerRow + 1 + (pointsPerRow * pointsPerRow));
        //        //System.out.println(i+numSquares+1);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + 1 + (pointsPerRow * pointsPerRow));
        //        //System.out.println(i);
        //        //textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.0f, 0.0f)));
        //        //textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.0f, 1.0f)));
        //        //textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f( 1.0f, 1.0f)));
        //        //textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f( 1.0f, 0.0f)));
//
        //    }
        //}

        //+y side
        //for (int y = 0; y < pointsPerRow - 1; y++) {
//
        //    for (int x = 0; x < pointsPerRow - 1; x++) {
//
        //        //System.out.println("(" + x + ", " + y + ")");
////
        //        //System.out.println(x + (y * pointsPerRow));
        //        //System.out.println(x + (y * pointsPerRow) + 1);
        //        //System.out.println(x + (y * pointsPerRow) + pointsPerRow);
        //        //System.out.println(x + (y * pointsPerRow) + pointsPerRow);
        //        //System.out.println(x + (y * pointsPerRow) + pointsPerRow + 1);
        //        //System.out.println(x + (y * pointsPerRow) + 1);
//
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + ((pointsPerRow * pointsPerRow) * 2));
        //        //System.out.println(i-1);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + 1 + ((pointsPerRow * pointsPerRow) * 2));
        //        //System.out.println(i);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + pointsPerRow + ((pointsPerRow * pointsPerRow) * 2));
        //        //System.out.println(i+numSquares);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + pointsPerRow + ((pointsPerRow * pointsPerRow) * 2));
        //        //System.out.println(i+numSquares);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + pointsPerRow + 1 + ((pointsPerRow * pointsPerRow) * 2));
        //        //System.out.println(i+numSquares+1);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + 1 + ((pointsPerRow * pointsPerRow) * 2));
        //        //System.out.println(i);
        //        //textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.0f, 0.0f)));
        //        //textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.0f, 1.0f)));
        //        //textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f( 1.0f, 1.0f)));
        //        //textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f( 1.0f, 0.0f)));
//
        //    }
        //}

        //-y side
        //for (int y = 0; y < pointsPerRow - 1; y++) {
//
        //    for (int x = 0; x < pointsPerRow - 1; x++) {
//
        //        //System.out.println("(" + x + ", " + y + ")");
////
        //        //System.out.println(x + (y * pointsPerRow));
        //        //System.out.println(x + (y * pointsPerRow) + 1);
        //        //System.out.println(x + (y * pointsPerRow) + pointsPerRow);
        //        //System.out.println(x + (y * pointsPerRow) + pointsPerRow);
        //        //System.out.println(x + (y * pointsPerRow) + pointsPerRow + 1);
        //        //System.out.println(x + (y * pointsPerRow) + 1);
//
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + ((pointsPerRow * pointsPerRow) * 3));
        //        //System.out.println(i-1);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + 1 + ((pointsPerRow * pointsPerRow) * 3));
        //        //System.out.println(i);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + pointsPerRow + ((pointsPerRow * pointsPerRow) * 3));
        //        //System.out.println(i+numSquares);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + pointsPerRow + ((pointsPerRow * pointsPerRow) * 3));
        //        //System.out.println(i+numSquares);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + pointsPerRow + 1 + ((pointsPerRow * pointsPerRow) * 3));
        //        //System.out.println(i+numSquares+1);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + 1 + ((pointsPerRow * pointsPerRow) * 3));
        //        //System.out.println(i);
        //        //textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.0f, 0.0f)));
        //        //textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.0f, 1.0f)));
        //        //textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f( 1.0f, 1.0f)));
        //        //textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f( 1.0f, 0.0f)));
//
        //    }
        //}

        //+z side
        //for (int y = 0; y < pointsPerRow - 1; y++) {
//
        //    for (int x = 0; x < pointsPerRow - 1; x++) {
//
        //        //System.out.println("(" + x + ", " + y + ")");
////
        //        //System.out.println(x + (y * pointsPerRow));
        //        //System.out.println(x + (y * pointsPerRow) + 1);
        //        //System.out.println(x + (y * pointsPerRow) + pointsPerRow);
        //        //System.out.println(x + (y * pointsPerRow) + pointsPerRow);
        //        //System.out.println(x + (y * pointsPerRow) + pointsPerRow + 1);
        //        //System.out.println(x + (y * pointsPerRow) + 1);
//
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + ((pointsPerRow * pointsPerRow) * 4));
        //        //System.out.println(i-1);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + 1 + ((pointsPerRow * pointsPerRow) * 4));
        //        //System.out.println(i);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + pointsPerRow + ((pointsPerRow * pointsPerRow) * 4));
        //        //System.out.println(i+numSquares);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + pointsPerRow + ((pointsPerRow * pointsPerRow) * 4));
        //        //System.out.println(i+numSquares);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + pointsPerRow + 1 + ((pointsPerRow * pointsPerRow) * 4));
        //        //System.out.println(i+numSquares+1);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + 1 + ((pointsPerRow * pointsPerRow) * 4));
        //        //System.out.println(i);
        //        //textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.0f, 0.0f)));
        //        //textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.0f, 1.0f)));
        //        //textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f( 1.0f, 1.0f)));
        //        //textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f( 1.0f, 0.0f)));
//
        //    }
        //}

        //-z side
        //for (int y = 0; y < pointsPerRow - 1; y++) {
//
        //    for (int x = 0; x < pointsPerRow - 1; x++) {
//
        //        //System.out.println("(" + x + ", " + y + ")");
////
        //        //System.out.println(x + (y * pointsPerRow));
        //        //System.out.println(x + (y * pointsPerRow) + 1);
        //        //System.out.println(x + (y * pointsPerRow) + pointsPerRow);
        //        //System.out.println(x + (y * pointsPerRow) + pointsPerRow);
        //        //System.out.println(x + (y * pointsPerRow) + pointsPerRow + 1);
        //        //System.out.println(x + (y * pointsPerRow) + 1);
//
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + ((pointsPerRow * pointsPerRow) * 5));
        //        //System.out.println(i-1);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + 1 + ((pointsPerRow * pointsPerRow) * 5));
        //        //System.out.println(i);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + pointsPerRow + ((pointsPerRow * pointsPerRow) * 5));
        //        //System.out.println(i+numSquares);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + pointsPerRow + ((pointsPerRow * pointsPerRow) * 5));
        //        //System.out.println(i+numSquares);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + pointsPerRow + 1 + ((pointsPerRow * pointsPerRow) * 5));
        //        //System.out.println(i+numSquares+1);
        //        indices = addIntToArray(indices, x + (y * pointsPerRow) + 1 + ((pointsPerRow * pointsPerRow) * 5));
        //        //System.out.println(i);
        //        //textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.0f, 0.0f)));
        //        //textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.0f, 1.0f)));
        //        //textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f( 1.0f, 1.0f)));
        //        //textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f( 1.0f, 0.0f)));
//
        //    }
        //}

        //for (int totPoints = (pointsPerRow - 1) * (pointsPerRow - 1))



        int skip = 1;

        for (int i = 1; i <= totPoints; i++) {

            skip++;

            indices = addIntToArray(indices, i-1);
            //System.out.println(i-1);
            indices = addIntToArray(indices, i);
            //System.out.println(i);
            indices = addIntToArray(indices, (i)+(numSquares));
            //System.out.println(i+numSquares);
            indices = addIntToArray(indices, (i)+(numSquares));
            //System.out.println(i+numSquares);
            indices = addIntToArray(indices, ((i)+(numSquares))+1);
            //System.out.println(i+numSquares+1);
            indices = addIntToArray(indices, i);
            //System.out.println(i);
            textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.0f, 0.0f)));
            textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.0f, 1.0f)));
            textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f( 1.0f, 1.0f)));
            textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f( 1.0f, 0.0f)));
            //indices = addIntToArray(indices, (i + 1) * numSquares);
            //indices = addIntToArray(indices, (i + 1) * numSquares);
            //indices = addIntToArray(indices, (i + 2) * numSquares);
            //indices = addIntToArray(indices, i + 1);

            if (skip == numSquares + 1) {

                i++;
                skip = 1;

            }

        }

        //vertices = addVectorToArray(vertices, new Vertex(new Vector3f((float) 0.49999997f, (float) -0.5f, (float) -0.70710677f)));
        //vertices = addVectorToArray(vertices, new Vertex(new Vector3f((float) 0.68301266f,(float) -0.18301271f,(float) -0.70710677f)));
        //vertices = addVectorToArray(vertices, new Vertex(new Vector3f((float) 0.68301266f,(float) -0.6830127f,(float) -0.25881904f)));


        for(int i=0;i<indices.length; i++) {
            System.out.println(indices[i]);
        }
        //System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        //System.out.println(indices.length);
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println(indices.length);
        //indices = new int[] {0, 1, 2};

        this.sphereMesh = new Mesh(vertices, textCoords, normals, indices);

        sphereMesh.create();

    }

    private Vertex[] addVectorToArray(Vertex[] newArray, Vertex elementToAdd) {

        Vertex[] destArray = new Vertex[newArray.length+1];

        for(int i = 0; i < newArray.length; i++) {
            destArray[i] = newArray[i];
        }

        destArray[destArray.length - 1] = elementToAdd;
        return destArray;

    }

    private int[] addIntToArray(int[] newIntArray, int elementToAdd) {

        int[] destArray = new int[newIntArray.length + 1];

        for(int i = 0; i < newIntArray.length; i++) {
            destArray[i] = newIntArray[i];
        }

        destArray[destArray.length - 1] = elementToAdd;
        return destArray;

    }

    public Mesh getMesh() {
        return sphereMesh;
    }

}
