/* Name: Joshau Jones
 * Email: jjo108@u.rochester.edu
 * Last modified: April 2 2023
 * Assignment: CSC 171 HW 5- UR Paint: An app utilizing graphics that allows user to place/drag squares and circles, and change their colors
 */
  

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

public class URPaint extends JFrame implements ChangeListener, ActionListener{
    // Initialize panels, arrays, and other variables
    JPanel ControlPanel;
    Canvas PaintPanel;
    JPanel PaintPanel2;
    JSlider redSlider;
    JSlider blueSlider;
    JSlider greenSlider;

    String buttonChoice = "Square";

    private ArrayList<Shape> shapes = new ArrayList<>();
    private Shape selectedShape = null;

    public URPaint() { 
        this.setTitle("PaintHW");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the canvas for drawing shapes
        PaintPanel = new Canvas();
        PaintPanel.setPreferredSize(new Dimension(700, 500));
        // Mouse Listener to change the "selectedShape Variable for movement and recoloing"
        PaintPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Check if the mouse click is within the bounds of an existing shape
                for (Shape shape : shapes) {
                    if (shape.contains(e.getX(), e.getY())) {
                        // If user clicks the same shape again, it deselects it
                        if (selectedShape == shape){
                            selectedShape = null;
                            PaintPanel.repaint();
                            return;
                        } else {
                            selectedShape = shape;
                            PaintPanel.repaint();
                            return;
                        }
                    }
                }

                // Add a new shape to the canvas when the mouse is pressed based on the button choice
                if (buttonChoice == "Square"){
                    int shapeSize = 30;
                    Shape newShape = new Rectangle(e.getX()-shapeSize/2, e.getY()-shapeSize/2, shapeSize, shapeSize);
                    newShape.setColor(Color.GREEN);
                    shapes.add(newShape);
                    selectedShape = newShape;
                } else if (buttonChoice == "Circle"){
                    int shapeSize = 30;
                    Shape newShape = new Oval(e.getX()-shapeSize/2, e.getY()-shapeSize/2, shapeSize, shapeSize);
                    newShape.setColor(Color.YELLOW);
                    shapes.add(newShape);
                    selectedShape = newShape;
                // Palette doesn't do anything - makes selectedShape into null so you can change color of background (need to still click on empty part of canvas)
                } else if (buttonChoice == "Palette"){ 
                    while (selectedShape!= null){
                        selectedShape = null;
                        PaintPanel.repaint();
                    }
                }
                PaintPanel.repaint();
            }
        });

        // To drag the shape:
        PaintPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedShape != null) {
                    // Move the selected shape to the new position
                    selectedShape.move(e.getX(), e.getY());
                    PaintPanel.repaint();
                }
            }
        });

        PaintPanel.setBackground(Color.BLACK);
        this.add(PaintPanel, BorderLayout.CENTER);

        // ControlPanel made of Button pannel and Slider Panel
        ControlPanel = new JPanel();
        ControlPanel.setPreferredSize(new Dimension(700, 95));
        ControlPanel.setLayout(new BoxLayout(ControlPanel, BoxLayout.Y_AXIS)); 
        this.add(ControlPanel, BorderLayout.PAGE_START);

        //Create the Button Panel to contain the buttons
        JPanel ButtonPanel = new JPanel();
        ButtonPanel.setLayout(new BoxLayout(ButtonPanel, BoxLayout.LINE_AXIS));
        ControlPanel.add(ButtonPanel);

        //Create the buttons and add it to the button panel
        JButton squareButton = new JButton("Square");
        squareButton.addActionListener(this);
        JButton circleButton = new JButton("Circle");
        circleButton.addActionListener(this);
        JButton paletteButton = new JButton("Palette");
        paletteButton.addActionListener(this);
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(this);

        // Clear button removes all shapes in the Shape array
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapes.clear();
                selectedShape = null;
                PaintPanel.repaint();
            }
        });

        ButtonPanel.add(squareButton);
        ButtonPanel.add(circleButton);
        ButtonPanel.add(paletteButton);
        ButtonPanel.add(clearButton);

        //Create the Slider Panel to contain the sliders
        JPanel SliderPanel = new JPanel();
        SliderPanel.setLayout(new BoxLayout(SliderPanel, BoxLayout.LINE_AXIS));
        SliderPanel.add(Box.createHorizontalStrut(10)); // Add vertical strut for spacing
        ControlPanel.add(SliderPanel);

        //Create the Sliders and add it to the slider panel
        JLabel redLabel = new JLabel("R");
        redSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        redSlider.addChangeListener(this);
        redSlider.setMajorTickSpacing(255);
        redSlider.setPaintLabels(true);
        redSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (selectedShape != null) {
                    selectedShape.setColor(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue()));
                    PaintPanel.repaint();
                } else {
                    Color newColor = new Color(redSlider.getValue(),greenSlider.getValue(), blueSlider.getValue());
                    PaintPanel.setBackground(newColor);
                }
            }
        });
        
        JLabel greenLabel = new JLabel("G");
        greenSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        greenSlider.addChangeListener(this);
        greenSlider.setMajorTickSpacing(255);
        greenSlider.setPaintLabels(true);
        greenSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (selectedShape != null) {
                    selectedShape.setColor(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue()));
                    PaintPanel.repaint();
                } else {
                    Color newColor = new Color(redSlider.getValue(),greenSlider.getValue(), blueSlider.getValue());
                    PaintPanel.setBackground(newColor);
                }
            }
        });

        JLabel blueLabel = new JLabel("B");
        blueSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        blueSlider.addChangeListener(this);
        blueSlider.setMajorTickSpacing(255);
        blueSlider.setPaintLabels(true);
        blueSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (selectedShape != null) {
                    selectedShape.setColor(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue()));
                    PaintPanel.repaint();
                } else {
                    Color newColor = new Color(redSlider.getValue(),greenSlider.getValue(), blueSlider.getValue());
                    PaintPanel.setBackground(newColor);
                }
            }
        });


        SliderPanel.add(redLabel);
        SliderPanel.add(redSlider);
        SliderPanel.add(greenLabel);
        SliderPanel.add(greenSlider);
        SliderPanel.add(blueLabel);
        SliderPanel.add(blueSlider);    

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    // Creat a class Shape with subclasses of Rectange and Oval
    private interface Shape {
        boolean contains(int x, int y);
        void paint(Graphics2D g);
        void setColor(Color color);
        void move(int x, int y);
        
    }
    
    private class Rectangle implements Shape {
        private int x, y, width, height;
        private Color color = Color.BLACK;
        
        public Rectangle(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
        
        @Override
        public boolean contains(int x, int y) {
            return x >= this.x && x < this.x + this.width && y >= this.y && y < this.y + this.height;
        }
        
        @Override
        public void paint(Graphics2D g) {
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }
        
        @Override
        public void setColor(Color color) {
            this.color = color;
        }

        @Override
        public void move(int x, int y) {
            this.x = x - width / 2;
            this.y = y - height / 2;
        }

    }

    private class Oval implements Shape {
        private int x, y, width, height;
        private Color color = Color.BLACK;
        
        public Oval(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
        
        @Override
        public boolean contains(int x, int y) {
            return x >= this.x && x < this.x + this.width && y >= this.y && y < this.y + this.height;
        }
        
        @Override
        public void paint(Graphics2D g) {
            g.setColor(color);
            g.fillOval(x, y, width, height);
        }
        
        @Override
        public void setColor(Color color) {
            this.color = color;
        }

        @Override
        public void move(int x, int y) {
            this.x = x - width / 2;
            this.y = y - height / 2;
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
    }

    @Override // Sets button Choice to the button clicked
    public void actionPerformed(ActionEvent e) {
		buttonChoice = e.getActionCommand();
        //System.out.println(buttonChoice);
    }

    private class Canvas extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            Graphics2D g2d = (Graphics2D) g;
            for (Shape shape : shapes) {
                shape.paint(g2d);
            }
        }
    }

    public static void main(String args[]){
        // Calls the class
        new URPaint();
    }
}