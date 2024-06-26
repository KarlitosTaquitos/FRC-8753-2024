// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.DriverConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.MoveIntakeInside;
import frc.robot.commands.MoveIntakeToAmp;
import frc.robot.commands.MoveIntakeToFloor;
import frc.robot.commands.MoveIntakeToShooter;
import frc.robot.commands.ResetDegree;
import frc.robot.commands.ResetIntakePosition;
import frc.robot.commands.ToggleClimberLimit;
import frc.robot.commands.ToggleDrivingMode;
import frc.robot.subsystems.Climbers;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LEDs;
import frc.robot.subsystems.Shooter;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // Subsystems
  public final DriveTrain driveTrain = new DriveTrain();
  private final Intake intake = new Intake();
  private final Shooter shooter = new Shooter();
  private final Climbers climbers = new Climbers();
  private final LEDs lights = new LEDs();

  private final XboxController driver = new XboxController(DriverConstants.controllerPort);
  private final XboxController operator = new XboxController(OperatorConstants.controllerPort);

  // Commands
  private final ToggleDrivingMode toggledriveMode = new ToggleDrivingMode(driveTrain);
  private final ResetDegree resetdegree = new ResetDegree(driveTrain);

  private final MoveIntakeToFloor moveIntakeToFloor = new MoveIntakeToFloor(intake);
  private final MoveIntakeInside moveIntakeInside = new MoveIntakeInside(intake);
  private final MoveIntakeToShooter moveIntakeToShooter = new MoveIntakeToShooter(intake);
  private final MoveIntakeToAmp moveIntakeToAmp = new MoveIntakeToAmp(intake);
  private final ResetIntakePosition resetIntakePosition = new ResetIntakePosition(intake);
  private final Command shootNote = Autos.shootNote(intake, shooter);

  private final ToggleClimberLimit toggleClimberLimit = new ToggleClimberLimit(climbers);

  // Buttons
  // driver
  private final JoystickButton driverLB = new JoystickButton(driver, DriverConstants.lB);
  private final JoystickButton driverStart = new JoystickButton(driver, DriverConstants.start);
  private final JoystickButton driverBack = new JoystickButton(driver, DriverConstants.back);
  private final JoystickButton driverRB = new JoystickButton(driver, DriverConstants.rB);
  private final JoystickButton driverA = new JoystickButton(driver, DriverConstants.a);
  private final JoystickButton driverY = new JoystickButton(driver, DriverConstants.y);
  private final POVButton driverUpDPAD = new POVButton(driver, 0);
  private final JoystickButton driverX = new JoystickButton(driver, DriverConstants.x);

  // operator
  private final JoystickButton operatorA = new JoystickButton(operator, OperatorConstants.a);
  private final JoystickButton operatorB = new JoystickButton(operator, OperatorConstants.b);
  private final JoystickButton operatorX = new JoystickButton(operator, OperatorConstants.x);
  private final JoystickButton operatorY = new JoystickButton(operator, OperatorConstants.y);
  private final JoystickButton operatorLB = new JoystickButton(operator, OperatorConstants.lB);
  private final JoystickButton operatorRB = new JoystickButton(operator, OperatorConstants.rB);
  private final JoystickButton operatorStart = new JoystickButton(operator, OperatorConstants.start);
  private final JoystickButton operatorBack = new JoystickButton(operator, OperatorConstants.back);
  private final POVButton leftDPAD = new POVButton(operator, 270);
  private final POVButton rightDPAD = new POVButton(operator, 90);
  private final POVButton upDPAD = new POVButton(operator, 0);

  private final Command startInMiddleTwoNote = Autos.startInMiddleTwoNote(driveTrain, intake, shooter);
  private final Command startInMiddleThreeNoteBlueLeft = Autos.startInMiddleThreeNoteBlueLeft(driveTrain, intake,
      shooter);
  private final Command startInMiddleThreeNoteRedRight = Autos.startInMiddleThreeNoteRedRight(driveTrain, intake,
      shooter);
  private final Command startInMiddleThreeNoteBlueRight = Autos.startInMiddleThreeNoteBlueRight(driveTrain, intake,
      shooter);
  private final Command startInMiddleThreeNoteRedLeft = Autos.startInMiddleThreeNoteRedLeft(driveTrain, intake,
      shooter);
  private final Command startSourceSideOneNoteTaxi = Autos.startSourceSideOneNoteTaxi(driveTrain, intake, shooter);
  private final Command startShootNote = Autos.shootNote(intake, shooter);

  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    m_chooser.setDefaultOption("Middle 2 Note", startInMiddleTwoNote);
    m_chooser.addOption("Blue Left 3 Note", startInMiddleThreeNoteBlueLeft);
    m_chooser.addOption("Red Right 3 Note", startInMiddleThreeNoteRedRight);
    m_chooser.addOption("Blue Right 3 Note", startInMiddleThreeNoteBlueRight);
    m_chooser.addOption("Red Left 3 Note", startInMiddleThreeNoteRedLeft);
    m_chooser.addOption("Shoot and Taxi", startSourceSideOneNoteTaxi);
    m_chooser.addOption("Shoot and Stay", startShootNote);
    SmartDashboard.putData(m_chooser);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link
   * XboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.XboxController Flight
   * joysticks}.
   */
  private void configureBindings() {
    // // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    // new Trigger(m_exampleSubsystem::exampleCondition)
    // .onTrue(new ExampleCommand(m_exampleSubsystem));

    // // Schedule `exampleMethodCommand` when the Xbox controller's B button is
    // // pressed,
    // // cancelling on release.
    // m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());

    operatorA.onTrue(moveIntakeToFloor);
    operatorY.onTrue(moveIntakeToAmp);

    operatorB.onTrue(new ParallelCommandGroup(
        moveIntakeToShooter,
        new InstantCommand(() -> {
          lights.makeGreen();
        }, lights)));

    operatorX.onTrue(new ParallelCommandGroup(
        moveIntakeInside,
        new InstantCommand(() -> {
          lights.makeRed();
        }, lights)));

    { // Intake in
      operatorLB
          .onTrue(
              new RunCommand(() -> {
                intake.intake();
              }, intake))
          .onFalse(
              new InstantCommand(() -> {
                intake.stopIntake();
              }, intake));
    }

    { // Intake out
      operatorRB
          .onTrue(
              new RunCommand(() -> {
                intake.outtake();
              }, intake))
          .onFalse(
              new InstantCommand(() -> {
                intake.stopIntake();
              }, intake));
    }

    // Intake Default (operator triggers)
    intake.setDefaultCommand(
        new RunCommand(() -> {
          intake.moveAtSpeed(operator.getRightTriggerAxis() - operator.getLeftTriggerAxis());
        }, intake));

    driverUpDPAD.onTrue(shootNote);

    { // Shoot to Speaker
      driverLB
          .onTrue(
              new RunCommand(() -> {
                shooter.shootSpeaker();
                operator.setRumble(RumbleType.kBothRumble, 0.5);
              }, shooter))
          .onFalse(
              new InstantCommand(() -> {
                shooter.stop();
                operator.setRumble(RumbleType.kBothRumble, 0);
              }, shooter));
    }
    {
      driverX.onTrue(new RunCommand(() -> {
        shooter.shootOver();
        operator.setRumble(RumbleType.kBothRumble, 0.5);
      }, shooter))
          .onFalse(
              new InstantCommand(() -> {
                shooter.stop();
                operator.setRumble(RumbleType.kBothRumble, 0);
              }, shooter));
    }

    { // Shoot through Robot
      driverA
          .onTrue(new RunCommand(() -> {
            shooter.shootThrough();
            operator.setRumble(RumbleType.kBothRumble, 0.5);
          }, shooter))
          .onFalse(new InstantCommand(() -> {
            shooter.stop();
            operator.setRumble(RumbleType.kBothRumble, 0);
          }, shooter));
    }

    {
      driverY
          .onTrue(new RunCommand(() -> {
            shooter.shootAmp();
            operator.setRumble(RumbleType.kBothRumble, .5);
          }, shooter))
          .onFalse(new InstantCommand(() -> {
            shooter.stop();
            operator.setRumble(RumbleType.kBothRumble, 0);
          }, shooter));
    }

    // Reset zero position for intake
    operatorBack.onTrue(resetIntakePosition);

    // Toggle Driving Mode
    driverStart.onTrue(toggledriveMode);

    // Reset Encoder
    driverBack.onTrue(resetdegree);

    // Brake
    /*
     * driverRB.onTrue(new InstantCommand(() -> {
     * driveTrain.drive(0, 0, 0, false, false);
     * driveTrain.setToBrake();
     * }, driveTrain))
     * .onFalse(new InstantCommand(() -> {
     * driveTrain.setToCoast();
     * }, driveTrain));
     */
    // DriveTrain Default (driver sticks)
    driveTrain.setDefaultCommand(
        new RunCommand(() -> {
          driveTrain.drive(
              driver.getRawAxis(DriverConstants.leftY),
              driver.getRawAxis(DriverConstants.leftX),
              driver.getRawAxis(DriverConstants.rightX),
              leftDPAD.getAsBoolean(),
              rightDPAD.getAsBoolean(),
              upDPAD.getAsBoolean(),
              driverRB.getAsBoolean(),
              true,
              driver.getRightTriggerAxis());
        }, driveTrain));

    operatorStart.onTrue(toggleClimberLimit);

    // Climbers Default (operator sticks)
    climbers.setDefaultCommand(
        new RunCommand(() -> {
          climbers.runClimbers(operator.getLeftY(), operator.getRightY());
        }, climbers));

    // Lights default
    lights.setDefaultCommand(
        new RunCommand(() -> {
          lights.setColor(
              shooter.getShooterVelocity());
        }, lights));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // TODO: Test and choose autos
    // return m_chooser.getSelected();
    return m_chooser.getSelected();
  }
}
