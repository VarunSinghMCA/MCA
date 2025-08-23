import nodemailer from 'nodemailer';

export const sendRegistrationEmail = async (to, username) => {
  const transporter = nodemailer.createTransport({
    service: 'gmail',
    auth: {
      user: process.env.EMAIL_USER,
      pass: process.env.EMAIL_PASS,
    },
  });

  const mailOptions = {
    from: process.env.EMAIL_USER,
    to,
    subject: 'Registration Successful',
    html: `<h2>Welcome, ${username}!</h2><p>Your registration was successful. Enjoy using our app!</p>`
  };

  await transporter.sendMail(mailOptions);
};
