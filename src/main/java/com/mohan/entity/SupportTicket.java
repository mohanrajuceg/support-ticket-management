package com.mohan.entity;

import com.mohan.entity.CancelledReason;
import com.mohan.entity.Status;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SupportTicket {

	String createdBy;

	String description;

	Status status;

	int severity;

	CancelledReason cancelledReason;

	String cancelledOtherDescription;

	String comments;

	public String getActiveFields() {

		StringBuilder builder = new StringBuilder();
		builder.append("Active Fields - [");
		builder.append("CreatedBy");
		if (createdBy != null && !createdBy.isEmpty()) {
			builder.append("['" + createdBy + "']");
		}

		builder.append(", ");
		builder.append("Description");
		if (description != null && !description.isEmpty()) {
			builder.append("['" + description + "']");
		}

		builder.append(", ");
		builder.append("Severity");
		if (getSeverity() > 0) {
			builder.append("[" + getSeverity() + "]");
		}

		builder.append(", ");
		builder.append("Status");
		if (status != null) {
			builder.append("['" + status + "']");
			if (Status.COMPLETED.equals(status)) {
				builder.append(", ");
				builder.append("Comments");
				if (comments != null && !comments.isEmpty()) {
					builder.append("['" + comments + "']");
				}
			} else {
				builder.append(getCancelFields());
			}
		}

		builder.append("]");
		return builder.toString();
	}

	private String getCancelFields() {

		StringBuilder builder = new StringBuilder();
		builder.append(", ");
		builder.append("CancelledReason");
		if (cancelledReason != null) {
			builder.append("['" + cancelledReason + "']");
			if (CancelledReason.OTHERS.equals(cancelledReason)) {
				builder.append(", ");
				builder.append("CancelledOtherDescription");
				if (cancelledOtherDescription != null) {
					builder.append("['" + cancelledOtherDescription + "']");
				}
			}
		}
		return builder.toString();
	}
}
