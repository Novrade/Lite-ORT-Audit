# **Lite‑ORT‑Audit**

Lite‑ORT‑Audit is a small toolset used during Infrastructure ORT performed by the LITE team before launching a new MDF.
The goal of this project is to help verify that the IT infrastructure in Demarc and MDF/SDEN is complete, configured correctly, and ready for operational handover.

### **Purpose**

During an Infrastructure ORT, the LITE team needs to confirm things such as:

required devices are installed and powered correctly,
network configuration is in place,
connectivity and redundancy meet expectations,
the physical and logical setup follows Amazon standards.

This project provides scripts and supporting files to make these checks more consistent and easier to document.

### **SQL Data Source**

The SQL data source used by some checks is not included in this repository.
It must be requested separately through the appropriate internal channels.

### **Features**
automated checks for core infrastructure items,
configurable test parameters,
logging of results for use in ORT reports,
modular structure that can be extended with additional test sets