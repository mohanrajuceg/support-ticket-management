package com.mohan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.mohan.entity.CancelledReason;
import com.mohan.entity.Status;
import com.mohan.entity.SupportTicket;

public class SupportTaskManagement {

	public static void main(String args[]) {
		System.out.println("**** Supprt Task Management System ****\n");

		init();
	}

	public static void init() {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			SupportTicket ticket = createNewTicket();
			System.out.println(ticket.getActiveFields());

			System.out.println("Update createdBy ");
			ticket.setCreatedBy(br.readLine());
			System.out.println(ticket.getActiveFields());

			System.out.println("Update description ");
			ticket.setDescription(br.readLine());
			System.out.println(ticket.getActiveFields());

			try {
				System.out.println("Update Severity between 1 to 10");
				ticket.setSeverity(Integer.parseInt(br.readLine()));
			} catch (NumberFormatException e) {
				System.out.println("Exception occured : " + e.getMessage());
			}
			System.out.println(ticket.getActiveFields());

			try {
				System.out.println("Update status ");
				ticket.setStatus(Status.of(br.readLine()));
				System.out.println(ticket.getActiveFields());
			} catch (IllegalArgumentException e) {
				System.out.println("Exception : " + e.getMessage());
				System.out.println("Do you want to update another ticket?");
				if (br.readLine().equalsIgnoreCase("yes")) {
					init();
				} else {
					return;
				}
			}

			if (Status.CANCELLED.equals(ticket.getStatus())) {
				try {
					System.out.println("Update CancelledReason ");
					ticket.setCancelledReason(CancelledReason.of(br.readLine()));
					System.out.println(ticket.getActiveFields());
				} catch (IllegalArgumentException e) {
					System.out.println("Exception : " + e.getMessage());
					System.out.println("Do you want to update another ticket?");
					if (br.readLine().equalsIgnoreCase("yes")) {
						init();
					}
				}

				if (CancelledReason.OTHERS.equals(ticket.getCancelledReason())) {
					System.out.println("Update CancelledOtherDescription ");
					ticket.setCancelledOtherDescription(br.readLine());
					System.out.println(ticket.getActiveFields());
				}
			} else {
				System.out.println("Update comments ");
				ticket.setComments(br.readLine());
				System.out.println(ticket.getActiveFields());
			}

			System.out.println("Do you want to update another ticket?");
			if (br.readLine().equalsIgnoreCase("yes")) {
				init();
			} else {
				br.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static SupportTicket createNewTicket() {
		SupportTicket newTicket = new SupportTicket();
		return newTicket;
	}

}
