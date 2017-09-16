package com.bernardof.pong;

import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by BernardoF on 13/09/2017.
 */

public class CollisionChecker {
    private enum CollisionSide {
        None,
        Left,
        Right,
        Top,
        Bottom
    }

    private enum IntersectionCorner {
        TopLeft,
        TopRight,
        BottomLeft,
        BottomRight
    }

    private class CollisionData {
        public CollisionSide side;
        public Point collisionPoint;
        public float dist = Float.MAX_VALUE;
        public IntersectionCorner corner;
    }


    public void checkCollision(Ball ball, Player paddle) {
        if (Rect.intersects(paddle.getHitbox(), ball.getHitbox())) {
            CollisionData leftCollisionData = new CollisionData();
            CollisionData rightCollisionData = new CollisionData();
            CollisionData topCollisionData = new CollisionData();
            CollisionData bottomCollisionData = new CollisionData();

            //Left side of the paddle
            if (ball.getHitbox().right > paddle.getHitbox().left) {

                Point rightTopIntersection = null;
                Point ballLastTopRight = new Point(ball.getLastRight(),
                        ball.getLastTop());
                if (isPointInsideOfRect(ballLastTopRight, paddle.getHitbox())) {
                    ballLastTopRight = getPointOutOfRect(ballLastTopRight,
                            ball.getMovementSpeedX(),
                            ball.getMovementSpeedY(),
                            paddle.getHitbox());
                }
                Point ballTopRight = new Point(ball.getHitbox().right,
                        ball.getHitbox().top);

                Point paddleTopLeft = new Point(paddle.getHitbox().left,
                        paddle.getHitbox().top);
                Point paddleBotomLeft = new Point(paddle.getHitbox().left,
                        paddle.getHitbox().bottom);

                rightTopIntersection = lineIntersection(ballLastTopRight,
                        ballTopRight,
                        paddleTopLeft,
                        paddleBotomLeft);

                Point rightBottomIntersection = null;
                Point ballLastBottomRight = new Point(ball.getLastRight(),
                        ball.getLastBottom());
                if (isPointInsideOfRect(ballLastBottomRight, paddle.getHitbox())) {
                    ballLastBottomRight = getPointOutOfRect(ballLastBottomRight,
                            ball.getMovementSpeedX(),
                            ball.getMovementSpeedY(),
                            paddle.getHitbox());
                }
                Point ballBottomRight = new Point(ball.getHitbox().right,
                        ball.getHitbox().bottom);

                rightBottomIntersection = lineIntersection(ballLastBottomRight,
                        ballBottomRight,
                        paddleTopLeft,
                        paddleBotomLeft);

                if (rightTopIntersection != null) {
                    leftCollisionData.collisionPoint = rightTopIntersection;
                    float topDist = calculateSqrtDistance(ballLastTopRight, rightTopIntersection);
                    leftCollisionData.dist = topDist;
                    //If has two intersections choose the nearest
                    if (rightBottomIntersection != null) {
                        float bottomDist = calculateSqrtDistance(ballLastBottomRight, rightBottomIntersection);
                        if (bottomDist < topDist) {
                            leftCollisionData.collisionPoint = rightBottomIntersection;
                            leftCollisionData.dist = bottomDist;
                        }
                    }
                } else if (rightBottomIntersection != null) {
                    leftCollisionData.collisionPoint = rightBottomIntersection;
                    float bottomDist = calculateSqrtDistance(ballLastBottomRight, rightBottomIntersection);
                    leftCollisionData.dist = bottomDist;
                }

            }

            //Right side of the paddle
            if (ball.getHitbox().left < paddle.getHitbox().right) {

                Point leftTopIntersection = null;
                Point ballLastTopLeft = new Point(ball.getLastLeft(),
                        ball.getLastTop());
                if (isPointInsideOfRect(ballLastTopLeft, paddle.getHitbox())) {
                    ballLastTopLeft = getPointOutOfRect(ballLastTopLeft,
                            ball.getMovementSpeedX(),
                            ball.getMovementSpeedY(),
                            paddle.getHitbox());
                }
                Point ballTopLeft = new Point(ball.getHitbox().left,
                        ball.getHitbox().top);

                Point paddleTopRight = new Point(paddle.getHitbox().right,
                        paddle.getHitbox().top);
                Point paddleBotomRight = new Point(paddle.getHitbox().right,
                        paddle.getHitbox().bottom);

                leftTopIntersection = lineIntersection(ballLastTopLeft,
                        ballTopLeft,
                        paddleTopRight,
                        paddleBotomRight);

                Point leftBottomIntersection = null;
                Point ballLastBottomLeft = new Point(ball.getLastLeft(),
                        ball.getLastBottom());
                if (isPointInsideOfRect(ballLastBottomLeft, paddle.getHitbox())) {
                    ballLastBottomLeft = getPointOutOfRect(ballLastBottomLeft,
                            ball.getMovementSpeedX(),
                            ball.getMovementSpeedY(),
                            paddle.getHitbox());
                }
                Point ballBottomLeft = new Point(ball.getHitbox().left,
                        ball.getHitbox().bottom);

                leftBottomIntersection = lineIntersection(ballLastBottomLeft,
                        ballBottomLeft,
                        paddleTopRight,
                        paddleBotomRight);

                if (leftTopIntersection != null) {
                    rightCollisionData.collisionPoint = leftTopIntersection;
                    float topDist = calculateSqrtDistance(ballLastTopLeft, leftTopIntersection);
                    rightCollisionData.dist = topDist;
                    //If has two intersections choose the nearest
                    if (leftBottomIntersection != null) {
                        float bottomDist = calculateSqrtDistance(ballLastBottomLeft, leftBottomIntersection);
                        if (bottomDist < topDist) {
                            rightCollisionData.collisionPoint = leftBottomIntersection;
                            rightCollisionData.dist = bottomDist;
                        }
                    }
                } else if (leftBottomIntersection != null) {
                    rightCollisionData.collisionPoint = leftBottomIntersection;
                    float bottomDist = calculateSqrtDistance(ballLastBottomLeft, leftBottomIntersection);
                    rightCollisionData.dist = bottomDist;
                }
            }

            //Top side of the paddle
            if (ball.getHitbox().bottom > paddle.getHitbox().top) {

                Point rightBottomIntersection = null;
                Point ballLastBottomRight = new Point(ball.getLastRight(),
                        ball.getLastBottom());
                Point ballBottomRight = new Point(ball.getHitbox().right,
                        ball.getHitbox().bottom);

                Point paddleTopLeft = new Point(paddle.getHitbox().left,
                        paddle.getHitbox().top);
                Point paddleTopRight = new Point(paddle.getHitbox().right,
                        paddle.getHitbox().top);

                rightBottomIntersection = lineIntersection(ballLastBottomRight,
                        ballBottomRight,
                        paddleTopLeft,
                        paddleTopRight);

                Point leftBottomIntersection = null;
                Point ballLastBottomLeft = new Point(ball.getLastLeft(),
                        ball.getLastBottom());
                Point ballBottomLeft = new Point(ball.getHitbox().left,
                        ball.getHitbox().bottom);

                leftBottomIntersection = lineIntersection(ballLastBottomLeft,
                        ballBottomLeft,
                        paddleTopLeft,
                        paddleTopRight);

                if (rightBottomIntersection != null) {
                    topCollisionData.collisionPoint = rightBottomIntersection;
                    float rightDist = calculateSqrtDistance(ballLastBottomRight, rightBottomIntersection);
                    topCollisionData.dist = rightDist;
                    //If has two intersections choose the nearest
                    if (leftBottomIntersection != null) {
                        float leftDist = calculateSqrtDistance(ballLastBottomLeft, leftBottomIntersection);
                        if (leftDist < rightDist) {
                            topCollisionData.collisionPoint = leftBottomIntersection;
                            topCollisionData.dist = leftDist;
                        }
                    }
                } else if (leftBottomIntersection != null) {
                    topCollisionData.collisionPoint = leftBottomIntersection;
                    float leftDist = calculateSqrtDistance(ballLastBottomLeft, leftBottomIntersection);
                    topCollisionData.dist = leftDist;
                }
            }

            //Bottom side of the paddle
            if (ball.getHitbox().top < paddle.getHitbox().bottom) {

                Point rightTopIntersection = null;
                Point ballLastTopRight = new Point(ball.getLastRight(),
                        ball.getLastTop());
                Point ballTopRight = new Point(ball.getHitbox().right,
                        ball.getHitbox().top);

                Point paddleBottomLeft = new Point(paddle.getHitbox().left,
                        paddle.getHitbox().bottom);
                Point paddleBottomRight = new Point(paddle.getHitbox().right,
                        paddle.getHitbox().bottom);

                rightTopIntersection = lineIntersection(ballLastTopRight,
                        ballTopRight,
                        paddleBottomLeft,
                        paddleBottomRight);

                Point leftTopIntersection = null;
                Point ballLastTopLeft = new Point(ball.getLastLeft(),
                        ball.getLastTop());
                Point ballTopLeft = new Point(ball.getHitbox().left,
                        ball.getHitbox().top);

                leftTopIntersection = lineIntersection(ballLastTopLeft,
                        ballTopLeft,
                        paddleBottomLeft,
                        paddleBottomRight);

                if (rightTopIntersection != null) {
                    bottomCollisionData.collisionPoint = rightTopIntersection;
                    float rightDist = calculateSqrtDistance(ballLastTopRight, rightTopIntersection);
                    bottomCollisionData.dist = rightDist;
                    //If has two intersections choose the nearest
                    if (leftTopIntersection != null) {
                        float leftDist = calculateSqrtDistance(ballLastTopLeft, leftTopIntersection);
                        if (leftDist < rightDist) {
                            bottomCollisionData.collisionPoint = leftTopIntersection;
                            bottomCollisionData.dist = leftDist;
                        }
                    }
                } else if (leftTopIntersection != null) {
                    bottomCollisionData.collisionPoint = leftTopIntersection;
                    float leftDist = calculateSqrtDistance(ballLastTopLeft, leftTopIntersection);
                    bottomCollisionData.dist = leftDist;
                }
            }

            CollisionData nearestCollisionData = new CollisionData();
            CollisionSide side = CollisionSide.None;
            if (leftCollisionData.collisionPoint != null) {
                nearestCollisionData = leftCollisionData;
                side = CollisionSide.Left;
            }
            if (rightCollisionData.collisionPoint != null && rightCollisionData.dist < nearestCollisionData.dist) {
                nearestCollisionData = rightCollisionData;
                side = CollisionSide.Right;
            }
            if (topCollisionData.collisionPoint != null && topCollisionData.dist < nearestCollisionData.dist) {
                nearestCollisionData = topCollisionData;
                side = CollisionSide.Top;
            }
            if (bottomCollisionData.collisionPoint != null && bottomCollisionData.dist < nearestCollisionData.dist) {
                nearestCollisionData = bottomCollisionData;
                side = CollisionSide.Bottom;
            }

            switch (side) {
                case Left:
                    ball.inverseSpeedX();
                    break;
                case Right:
                    ball.inverseSpeedX();
                    break;
                case Top:
                    ball.inverseSpeedY();
                    break;
                case Bottom:
                    ball.inverseSpeedY();
                    break;
            }

            //The paddle movement influences the ball movement
            int movementDirection = paddle.getX() - paddle.getLastX();
            if (movementDirection != 0 && (side == CollisionSide.Bottom || side == CollisionSide.Top)) {
                if ((movementDirection > 0 && ball.getMovementSpeedX() >= 0) ||
                        (movementDirection < 0 && ball.getMovementSpeedX() <= 0)) {
                    ball.addToBonusSpeedX(10);
                } else {
                    ball.addToBonusSpeedX(-10);
                }
            }
        }
    }

    private Point getPointOutOfRect(Point point, int movementSpeedX, int movementSpeedY, Rect hitbox) {
        while (isPointInsideOfRect(point, hitbox)) {
            point.x -= movementSpeedX;
            point.y -= movementSpeedY;
        }
        return point;
    }

    private boolean isPointInsideOfRect(Point point, Rect rect) {
        //If point is out of rect return false
        if ((rect.left > point.x || point.x > rect.right) || (rect.top > point.y || point.y > rect.bottom)) {
            return false;
        } else {
            return true;
        }
    }

    private float calculateSqrtDistance(Point a, Point b) {
        return (float) Math.abs(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
    }

    private Point lineIntersection(Point p1, Point p2, Point p3, Point p4) {
        float Ax, Bx, Cx, Ay, By, Cy, d, e, f, num;
        float x1lo, x1hi, y1lo, y1hi;

        Ax = p2.x - p1.x;
        Bx = p3.x - p4.x;

        // X bound box test/
        if (Ax < 0) {
            x1lo = p2.x;
            x1hi = p1.x;
        } else {
            x1hi = p2.x;
            x1lo = p1.x;
        }

        if (Bx > 0) {
            if (x1hi < p4.x || p3.x < x1lo) return null;
        } else {
            if (x1hi < p3.x || p4.x < x1lo) return null;
        }

        Ay = p2.y - p1.y;
        By = p3.y - p4.y;

        // Y bound box test//
        if (Ay < 0) {
            y1lo = p2.y;
            y1hi = p1.y;
        } else {
            y1hi = p2.y;
            y1lo = p1.y;
        }

        if (By > 0) {
            if (y1hi < p4.y || p3.y < y1lo) return null;
        } else {
            if (y1hi < p3.y || p4.y < y1lo) return null;
        }

        Cx = p1.x - p3.x;
        Cy = p1.y - p3.y;
        d = By * Cx - Bx * Cy;  // alpha numerator//
        f = Ay * Bx - Ax * By;  // both denominator//

        // alpha tests//
        if (f > 0) {
            if (d < 0 || d > f) return null;
        } else {
            if (d > 0 || d < f) return null;
        }

        e = Ax * Cy - Ay * Cx;  // beta numerator//
        // beta tests //
        if (f > 0) {
            if (e < 0 || e > f) return null;

        } else {
            if (e > 0 || e < f) return null;
        }

        // check if they are parallel
        if (f == 0) return null;

        Point intersection = new Point();
        // compute intersection coordinates //
        num = d * Ax; // numerator //
        intersection.x = (int) (p1.x + num / f);

        num = d * Ay;
        intersection.y = (int) (p1.y + num / f);

        return intersection;

    }
}
