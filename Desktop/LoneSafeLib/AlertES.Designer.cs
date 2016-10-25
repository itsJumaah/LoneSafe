namespace LoneSafeLib
{
    partial class AlertES
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(AlertES));
            this.dismiss = new System.Windows.Forms.Button();
            this.map = new System.Windows.Forms.WebBrowser();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.longitude = new System.Windows.Forms.TextBox();
            this.latitude = new System.Windows.Forms.TextBox();
            this.label7 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.label9 = new System.Windows.Forms.Label();
            this.header = new System.Windows.Forms.Label();
            this.email = new System.Windows.Forms.Label();
            this.phone = new System.Windows.Forms.Label();
            this.rego = new System.Windows.Forms.Label();
            this.mobile = new System.Windows.Forms.Label();
            this.username = new System.Windows.Forms.Label();
            this.lname = new System.Windows.Forms.Label();
            this.fname = new System.Windows.Forms.Label();
            this.groupBox1.SuspendLayout();
            this.SuspendLayout();
            // 
            // dismiss
            // 
            this.dismiss.Location = new System.Drawing.Point(66, 438);
            this.dismiss.Name = "dismiss";
            this.dismiss.Size = new System.Drawing.Size(75, 23);
            this.dismiss.TabIndex = 0;
            this.dismiss.Text = "Dismiss";
            this.dismiss.UseVisualStyleBackColor = true;
            this.dismiss.Click += new System.EventHandler(this.dismiss_Click);
            // 
            // map
            // 
            this.map.Location = new System.Drawing.Point(214, 12);
            this.map.MinimumSize = new System.Drawing.Size(20, 20);
            this.map.Name = "map";
            this.map.Size = new System.Drawing.Size(489, 449);
            this.map.TabIndex = 1;
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.longitude);
            this.groupBox1.Controls.Add(this.latitude);
            this.groupBox1.Controls.Add(this.label7);
            this.groupBox1.Controls.Add(this.label6);
            this.groupBox1.ForeColor = System.Drawing.Color.MediumBlue;
            this.groupBox1.Location = new System.Drawing.Point(12, 294);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(196, 114);
            this.groupBox1.TabIndex = 2;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Location Coordinates";
            // 
            // longitude
            // 
            this.longitude.Location = new System.Drawing.Point(9, 80);
            this.longitude.Name = "longitude";
            this.longitude.ReadOnly = true;
            this.longitude.Size = new System.Drawing.Size(181, 20);
            this.longitude.TabIndex = 8;
            // 
            // latitude
            // 
            this.latitude.Location = new System.Drawing.Point(9, 41);
            this.latitude.Name = "latitude";
            this.latitude.ReadOnly = true;
            this.latitude.Size = new System.Drawing.Size(181, 20);
            this.latitude.TabIndex = 7;
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.ForeColor = System.Drawing.Color.RoyalBlue;
            this.label7.Location = new System.Drawing.Point(6, 64);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(57, 13);
            this.label7.TabIndex = 6;
            this.label7.Text = "Longitude:";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.ForeColor = System.Drawing.Color.RoyalBlue;
            this.label6.Location = new System.Drawing.Point(6, 25);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(48, 13);
            this.label6.TabIndex = 5;
            this.label6.Text = "Latitude:";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.ForeColor = System.Drawing.Color.RoyalBlue;
            this.label1.Location = new System.Drawing.Point(12, 47);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(60, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "First Name:";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.ForeColor = System.Drawing.Color.RoyalBlue;
            this.label2.Location = new System.Drawing.Point(12, 83);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(61, 13);
            this.label2.TabIndex = 1;
            this.label2.Text = "Last Name:";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.ForeColor = System.Drawing.Color.RoyalBlue;
            this.label3.Location = new System.Drawing.Point(12, 118);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(58, 13);
            this.label3.TabIndex = 2;
            this.label3.Text = "Username:";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.ForeColor = System.Drawing.Color.RoyalBlue;
            this.label4.Location = new System.Drawing.Point(12, 154);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(41, 13);
            this.label4.TabIndex = 3;
            this.label4.Text = "Mobile:";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.ForeColor = System.Drawing.Color.RoyalBlue;
            this.label5.Location = new System.Drawing.Point(12, 190);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(74, 13);
            this.label5.TabIndex = 4;
            this.label5.Text = "Vehicle Rego:";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.ForeColor = System.Drawing.Color.RoyalBlue;
            this.label8.Location = new System.Drawing.Point(12, 223);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(72, 13);
            this.label8.TabIndex = 5;
            this.label8.Text = "Home Phone:";
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.ForeColor = System.Drawing.Color.RoyalBlue;
            this.label9.Location = new System.Drawing.Point(12, 256);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(35, 13);
            this.label9.TabIndex = 6;
            this.label9.Text = "Email:";
            // 
            // header
            // 
            this.header.AutoSize = true;
            this.header.Font = new System.Drawing.Font("Microsoft Sans Serif", 11.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.header.ForeColor = System.Drawing.Color.MediumBlue;
            this.header.Location = new System.Drawing.Point(12, 12);
            this.header.Name = "header";
            this.header.Size = new System.Drawing.Size(56, 18);
            this.header.TabIndex = 7;
            this.header.Text = "Header";
            // 
            // email
            // 
            this.email.AutoSize = true;
            this.email.ForeColor = System.Drawing.Color.MediumBlue;
            this.email.Location = new System.Drawing.Point(12, 269);
            this.email.Name = "email";
            this.email.Size = new System.Drawing.Size(27, 13);
            this.email.TabIndex = 8;
            this.email.Text = "N/A";
            // 
            // phone
            // 
            this.phone.AutoSize = true;
            this.phone.ForeColor = System.Drawing.Color.MediumBlue;
            this.phone.Location = new System.Drawing.Point(12, 236);
            this.phone.Name = "phone";
            this.phone.Size = new System.Drawing.Size(27, 13);
            this.phone.TabIndex = 9;
            this.phone.Text = "N/A";
            // 
            // rego
            // 
            this.rego.AutoSize = true;
            this.rego.ForeColor = System.Drawing.Color.MediumBlue;
            this.rego.Location = new System.Drawing.Point(12, 202);
            this.rego.Name = "rego";
            this.rego.Size = new System.Drawing.Size(27, 13);
            this.rego.TabIndex = 10;
            this.rego.Text = "N/A";
            // 
            // mobile
            // 
            this.mobile.AutoSize = true;
            this.mobile.ForeColor = System.Drawing.Color.MediumBlue;
            this.mobile.Location = new System.Drawing.Point(12, 167);
            this.mobile.Name = "mobile";
            this.mobile.Size = new System.Drawing.Size(27, 13);
            this.mobile.TabIndex = 11;
            this.mobile.Text = "N/A";
            // 
            // username
            // 
            this.username.AutoSize = true;
            this.username.ForeColor = System.Drawing.Color.MediumBlue;
            this.username.Location = new System.Drawing.Point(12, 131);
            this.username.Name = "username";
            this.username.Size = new System.Drawing.Size(27, 13);
            this.username.TabIndex = 12;
            this.username.Text = "N/A";
            // 
            // lname
            // 
            this.lname.AutoSize = true;
            this.lname.ForeColor = System.Drawing.Color.MediumBlue;
            this.lname.Location = new System.Drawing.Point(12, 96);
            this.lname.Name = "lname";
            this.lname.Size = new System.Drawing.Size(27, 13);
            this.lname.TabIndex = 13;
            this.lname.Text = "N/A";
            // 
            // fname
            // 
            this.fname.AutoSize = true;
            this.fname.ForeColor = System.Drawing.Color.MediumBlue;
            this.fname.Location = new System.Drawing.Point(12, 60);
            this.fname.Name = "fname";
            this.fname.Size = new System.Drawing.Size(27, 13);
            this.fname.TabIndex = 14;
            this.fname.Text = "N/A";
            // 
            // AlertES
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.Cornsilk;
            this.ClientSize = new System.Drawing.Size(715, 473);
            this.Controls.Add(this.fname);
            this.Controls.Add(this.lname);
            this.Controls.Add(this.username);
            this.Controls.Add(this.mobile);
            this.Controls.Add(this.rego);
            this.Controls.Add(this.phone);
            this.Controls.Add(this.email);
            this.Controls.Add(this.header);
            this.Controls.Add(this.label9);
            this.Controls.Add(this.label8);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.map);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.dismiss);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.label2);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "AlertES";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Alert";
            this.TopMost = true;
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button dismiss;
        private System.Windows.Forms.WebBrowser map;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.TextBox longitude;
        private System.Windows.Forms.TextBox latitude;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.Label header;
        private System.Windows.Forms.Label email;
        private System.Windows.Forms.Label phone;
        private System.Windows.Forms.Label rego;
        private System.Windows.Forms.Label mobile;
        private System.Windows.Forms.Label username;
        private System.Windows.Forms.Label lname;
        private System.Windows.Forms.Label fname;
    }
}